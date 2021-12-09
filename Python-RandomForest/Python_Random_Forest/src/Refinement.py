'''
This module contains the definition of the main function of the  
program.  

Created on Oct 29, 2020

@author: Wilson Melendez
'''

from InitialProcesses import initialProcesses
from MiddleProcesses import middleProcesses
#from Replace_Null_with_None import replace_null_with_none
#from Write_to_CSV import write_to_csv
#from Delete_Concatenated_Columns import delete_concatenated_columns
#from Delete_Columns_With_All_Equal_Values import delete_columns_with_all_equal_values
#from Process_Units import process_data_units
#from Remove_Rows_NoResults import remove_rows_with_no_results
#from Delete_Merged_Columns import delete_merged_columns
#from Fix_Categorical_Data_Errors import fix_categorical_data
from Encode_Categorical_Data import encode_categorical_columns
#from Delete_Unwanted_Columns import delete_unwanted_columns
from Impute_Numerical_Columns import impute_missing_data_of_numerical_columns
from Perform_Multivariate_Imputation import iteratively_impute_numerical_columns
from UtilRecords import read_from_csv, write_to_csv, delete_columns_with_all_equal_values

def main():
    input_file = "..\\data\\assay_all_vw_out_22325rows.csv"
    
    output_Viability_Rows = "data\\Viability_Results_Rows.csv"
    output_NonEmptyColumns_Viability_Rows = "data\\Viability_Results_Rows_NonEmptyColumns.csv"
    output_Imputed_Values = "data\\Imputed_Numerical_Columns.csv"
    output_Multivariate_Imputed_Values = "data\\Multivariate_Imputed_Numerical_Columns.csv"
    
    # initial processes only
    df = initialProcesses(input_file)
    
    df = middleProcesses(df)
    
    # Encode categorical data
    df = encode_categorical_columns(df)

    # Extract only the rows with viability results
    df = extract_desired_rows("expression levels", df)
    
    # Write DataFrame to CSV file.
    write_to_csv(df, output_Viability_Rows)
    
    # Delete columns with the same value
    delete_columns_with_all_equal_values(df)
    
    # Write DataFrame to CSV file
    write_to_csv(df, output_NonEmptyColumns_Viability_Rows)
    
    # Impute missing data of numerical columns.
    # df = impute_missing_data_of_numerical_columns(df)
    #df = iteratively_impute_numerical_columns(df)
    
    # Write imputed DataFrame to a CSV file
    # write_to_csv(df, output_Imputed_Values)
    write_to_csv(df, output_Multivariate_Imputed_Values)
    
    print("Refinement Complete")
    
def extract_desired_rows(desired_result, df):
    column_name = desired_result+" result_value"
    df1 = df.loc[df[column_name].isna() == False]
    
    # Reset the rows indices.
    df1 = df1.reset_index(level = 0, drop = True)
    
    return df1

if __name__ == "__main__":
    main()
        
    