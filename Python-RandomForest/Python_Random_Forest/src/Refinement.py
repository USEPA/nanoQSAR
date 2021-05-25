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
from Remove_Rows_NoResults import remove_rows_with_no_results
from Delete_Merged_Columns import delete_merged_columns
from Fix_Categorical_Data_Errors import fix_categorical_data
from Encode_Categorical_Data import encode_categorical_columns
from Delete_Unwanted_Columns import delete_unwanted_columns
from Extract_Viability_Rows import extract_viability_rows
from Impute_Numerical_Columns import impute_missing_data_of_numerical_columns
from Perform_Multivariate_Imputation import iteratively_impute_numerical_columns

def main():
    input_file = "..\\data\\assay_all_vw_out_22325rows.csv"
    invitro_output = "data\\inVitro_Rows.csv"
    invitro_input = "..\\data\\inVitro_Rows.csv"
    output_file = "data\\assay_all_vw_out_inVitro.csv"
    output_NoConcatenatedFields = "data\\inVitro_No_Concatenated_Fields.csv"
    output_DifferentValues = "data\\inVitro_Columns_with_Different_Values.csv"
    output_procesed_units = "data\\inVitro_processed_units.csv"
    output_post_processed_units = "data\\inVitro_post_processed_units.csv"
    output_fixedCategoricalData = "data\\inVitro_fixed_categorical.data.csv"
    output_Viability_Rows = "data\\Viability_Results_Rows.csv"
    output_NonEmptyColumns_Viability_Rows = "data\\Viability_Results_Rows_NonEmptyColumns.csv"
    output_Imputed_Values = "data\\Imputed_Numerical_Columns.csv"
    output_Multivariate_Imputed_Values = "data\\Multivariate_Imputed_Numerical_Columns.csv"
    
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
    replace_null_with_none(df)
    
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
    replace_null_with_none(df)
    
    # Process units
    process_data_units(df)
    
    # Write DataFrame with processed units to a CSV file.
    write_to_csv(df, output_procesed_units)
    
    # Remove columns that were merged with other columns.
    delete_merged_columns(df)
    
    # Remove rows that have no results
    remove_rows_with_no_results(df)
    
    # Delete columns with the same value
    # This step is necessary because if rows with no results are removed (see step above), 
    # there is the possibility of having columns that are now empty.
    delete_columns_with_all_equal_values(df)
    
    # Write DataFrame with rows that have no results removed
    write_to_csv(df, output_post_processed_units)
    
    # Fix categorical data typos and misspellings.
    fix_categorical_data(df)
    
    # Write DataFrame with fixed categorical data
    write_to_csv(df, output_fixedCategoricalData)
    
    # Delete columns that have no predictive capabilities.
    # These columns will not be needed for the Random Forest analysis.
    delete_unwanted_columns(df)
    
    # Encode categorical data
    df = encode_categorical_columns(df)
    
    # Extract only the rows with viability results
    df = extract_viability_rows(df)
    
    # Write DataFrame to CSV file.
    write_to_csv(df, output_Viability_Rows)
    
    # Delete columns with the same value
    delete_columns_with_all_equal_values(df)
    
    # Write DataFrame to CSV file
    write_to_csv(df, output_NonEmptyColumns_Viability_Rows)
    
    # Impute missing data of numerical columns.
    # df = impute_missing_data_of_numerical_columns(df)
    df = iteratively_impute_numerical_columns(df)
    
    # Write imputed DataFrame to a CSV file
    # write_to_csv(df, output_Imputed_Values)
    write_to_csv(df, output_Multivariate_Imputed_Values)
    
    print("Refinement Complete")

if __name__ == "__main__":
    main()
        
    