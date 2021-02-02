'''
This module contains the definition of the main function of the  
program.  

Created on Oct 29, 2020

@author: Wilson Melendez
'''

import pandas as pd
from Parameters_Fields import split_parameters_fields
from Contaminant_Fields import split_contaminant_fields
from Additive_Fields import split_additive_fields
from Result_Fields import split_result_fields
from Select_in_vitro_Rows import select_in_vitro_rows
from Replace_Null_with_None import replace_null_with_none
from Write_to_CSV import write_to_csv
from Delete_Concatenated_Columns import delete_concatenated_columns
from Delete_Columns_With_All_Equal_Values import delete_columns_with_all_equal_values
from Process_Units import process_data_units

def main():
    input_file = "..\\data\\assay_all_vw_out_22325rows.csv"
    invitro_output = "data\\inVitro_Rows.csv"
    invitro_input = "..\\data\\inVitro_Rows.csv"
    output_file = "data\\assay_all_vw_out_inVitro.csv"
    output_NoConcatenatedFields = "data\\inVitro_No_Concatenated_Fields.csv"
    output_DifferentValues = "data\\inVitro_Columns_with_Different_Values.csv"
    output_procesed_units = "data\\inVitro_processed_units.csv"
    
    # Read CSV file. 
    # Note that we must specify the right type of encoding in order to read in all characters
    # correctly.  Some of the data contain Greek letters which me must account for. 
    df = pd.read_csv(input_file, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    # Select in vitro rows
    df = select_in_vitro_rows(df)
    
    # Write inVitro rows to output
    write_to_csv(df, invitro_output)
    
    df = pd.read_csv(invitro_input, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    # Replace NULL with None.
    df = replace_null_with_none(df)
    
    # Proceed to split up the concatenated fields.
    split_parameters_fields(df)
    split_contaminant_fields(df)
    split_additive_fields(df)
    split_result_fields(df)
                    
    # Write data frame to a CSV file.
    write_to_csv(df, output_file)
    
    # Remove concatenated columns
    delete_concatenated_columns(df)
    
    # Write DataFrame to a CSV file.
    write_to_csv(df, output_NoConcatenatedFields)

    # Delete columns with the same value
    delete_columns_with_all_equal_values(df)
    
    # Write DataFrame to a CSV file.
    write_to_csv(df, output_DifferentValues)
    
    # Read CSV file back into the program.
    input_filename = "..\\" + output_DifferentValues
    df = pd.read_csv(input_filename, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    # Replace NULL with None.
    df = replace_null_with_none(df)
    
    # Process units
    process_data_units(df)
    
    # Write DataFrame with processed units to a CSV file.
    write_to_csv(df, output_procesed_units)

if __name__ == "__main__":
    main()
        
    