'''
Created on Oct 29, 2020

@author: Wilson Melendez
'''

import pandas as pd
from Parameters_Fields import split_parameters_fields
from Contaminant_Fields import split_contaminant_fields
from Additive_Fields import split_additive_fields
from Result_Fields import split_result_fields


def main():
    input_file = "E:\\Random_Forest_Project\\assay_all_vw_out_22325rows.csv"
    invitro_output = "E:\\Random_Forest_Project\\inVitro_Rows.csv"
    output_file = "E:\\Random_Forest_Project\\assay_all_vw_out_inVitro.csv"
    
    # Read CSV file.
    df = pd.read_csv(input_file, na_values = "NULL", skip_blank_lines = False, keep_default_na = True, na_filter = False, low_memory = False)
    
    # Select in vitro rows only.
    # Lowercase and remove extra white space from the strings before selecting the in vitro rows.
    df["assayType"] = df["assayType"].str.lower().str.strip()
    df = df[df.assayType == 'in vitro']
    
    # Reset the rows indices.
    df = df.reset_index(level = 0, drop = True) 
    
    # Replace NULL to None.
    df = df.replace({'NULL': None})
    
    # Write inVitro rows to output
    df.to_csv(invitro_output)
    
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
    df.to_csv(output_file)


if __name__ == "__main__":
    main()
        
    