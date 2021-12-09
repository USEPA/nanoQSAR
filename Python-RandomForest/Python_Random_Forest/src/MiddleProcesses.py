'''
Created on Nov 29, 2021

@author: PHARTEN
'''

from UtilRecords import read_from_csv, write_to_csv, delete_columns_with_all_equal_values
from Process_Units import process_data_units
from Fix_Categorical_Data_Errors import fix_categorical_data
from Delete_Unwanted_Columns import delete_unwanted_columns

def middleProcesses(df):
    '''
    these middle processes:
    1) translates units from one to another (e.g. from joules to eVolts)
    2) fix misspellings in categorical values
    3) encodes categorical values using one-hot encoding
    '''
    output_procesed_units = "data\\inVitro_processed_units.csv"
    output_post_processed_units = "data\\inVitro_post_processed_units.csv"
    output_fixedCategoricalData = "data\\inVitro_fixed_categorical.data.csv"
    
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
    
    return df

def replace_null_with_none(df):
    '''
    Name
    ----
    replace_null_with_none
    
    Description
    -----------
    This function replaces NULL entries with Python's None object.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the in vitro rows.
    
    Output Parameters
    -----------------
    Modified DataFrame df.
    
    '''
    # Replace all NULL entries with Python's object.
    df.replace({'NULL': None}, inplace = True)
    
    # Replace empty string with None.
    df.replace({"": None}, inplace = True) 

def delete_merged_columns(df):
    '''
    Name
    ----
    delete_merged_columns
    
    Description
    -----------
    This function deletes columns that had been merged with other columns.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the inVitro data.
    
    Output Parameters
    -----------------
    Modified DataFrame df.
    '''
    # Create list with columns to delete from DataFrame.  
    # These columns were merged with other columns.
    list_merged_columns = ['particle concentration log parameter_value',
                           'particle concentration log parameter_unit',
                           'particle concentration log parameter_nonnum',
                           'penicilin additive_value', 
                           'penicilin additive_unit',
                           'relative fluorescence result_value', 
                           'relative fluorescence result_unit']
                        
    # Delete the merged columns
    df.drop(list_merged_columns, axis = 1, inplace = True)

def remove_rows_with_no_results(df):
    # Extract the column headers
    col_names = list(df.columns)
    
    # Extract the columns headers associated with the values of the results
    subs_value = "result_value"
    list_result_value = [icol for icol in col_names if subs_value in icol]
    
    # Determine number of rows in data frame.
    nrow = len(df.index)
    
    for irow in range(0, nrow):
        # Check whether all result values are empty for this row.
        if (df.loc[irow, list_result_value].isna().values.all()):
            # Delete DataFrame row
            df.drop(index = irow, inplace = True)
            
    # Reset the rows indices. 
    df.reset_index(level = 0, drop = True, inplace = True)
    #print("rows =",nrow,", no results =",nrow-len(df.index))
    