'''
Created on Nov 29, 2021

@author: PHARTEN
'''

import pandas as pd
from pathlib import Path

def read_from_csv(input_file):
    '''
    Name
    ----
    read_from_csv
    
    Description
    -----------
    This function reads a DataFrame from a CSV file.
    
    Input Parameters
    ----------------
    # input_file, a csv file. 
    # Note that we must specify the right type of encoding in order to read in all characters
    # correctly.  Some of the data contain Greek letters which me must account for.
    '''
    
    if not Path(input_file).exists():
        input_file = "..\\" + input_file
    
    df = pd.read_csv(input_file, skip_blank_lines = False, 
                     na_filter = False, low_memory = False,
                     encoding = 'utf-8-sig')
    
    return df
    
def write_to_csv(df, file_output):
    '''
    Name
    ----
    write_to_csv
    
    Description
    -----------
    This function writes a DataFrame to a CSV file.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the in vitro rows.
    file_output: text
        output file name
    '''
    if not Path(file_output).exists():
        file_output = "..\\" + file_output
        
    # Write DataFrame to output.
    # Note that we must specify the right type of encoding to write out all characters correctly.
    # Some of the data contain Greek letters which need to be accounted for when writing to a CSV file.
    df.replace({None,'Null'}).to_csv(file_output, encoding = 'utf-8-sig', index = False)
    
        # Print message to console indicating that writing to CSV has completed.
    print("Writing of " + file_output + " to a CSV file has completed.")

def delete_columns_with_all_equal_values(df, keepUnits = True):
    '''
    Name
    ----
    delete_columns_with_all_equal_values
    
    Description
    -----------
    This function deletes columns in DataFrame that have the same value repeated in all their rows.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the inVitro data.
        
    Output Parameters
    -----------------
    Modified DataFrame df.
    '''

    for column in df:
        if (keepUnits and ("_unit" in column or "Unit" in column)):
            continue
        if (df[column].eq(df[column].iloc[0]).all() == True):
            df.drop(column, axis = 1, inplace = True)
        elif (df[column].isna().values.all() == True):
            df.drop(column, axis = 1, inplace = True)

    return df
