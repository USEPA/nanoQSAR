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

def main():
    input_file = "..\\data\\assay_all_vw_out_22325rows.csv"
    invitro_output = "..\\data\\inVitro_Rows.csv"
    output_file = "data\\assay_all_vw_out_inVitro.csv"
    output_NoConcatenatedFields = "data\\inVitro_No_Concatenated_Fields.csv"
    output_DifferentValues = "data\\inVitro_Columns_with_Different_Values.csv"
    
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
    
    df = pd.read_csv(invitro_output, na_values = "NULL", skip_blank_lines = False, 
                     keep_default_na = True, na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
        # Replace NULL with None.
    df = replace_null_with_none(df)
    
    # Extract column names.
    col_names = list(df.columns)
    
    # Determine number of rows in data frame.
    nrow = len(df.index)
    
    # Proceed to split up the concatenated fields.
    split_parameters_fields(df, nrow, col_names)
    split_contaminant_fields(df, nrow, col_names)
    split_additive_fields(df, nrow, col_names)
    split_result_fields(df, nrow, col_names)
                    
    # Write data frame to a CSV file.
    write_to_csv(df, output_file)
    
    # Remove concatenated columns
    delete_concatenated_columns(df, col_names)
    
    # Write DataFrame to a CSV file.
    write_to_csv(df, output_NoConcatenatedFields)

    # Delete columns with the same value
    delete_columns_with_all_equal_values(df)
    
    # Write DataFrame to a CSV file.
    write_to_csv(df, output_DifferentValues)

if __name__ == "__main__":
    main()
        
    