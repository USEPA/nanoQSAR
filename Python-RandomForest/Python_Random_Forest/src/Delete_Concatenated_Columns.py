'''
This module contains the definition of a function used to delete columns with 
concatenated fields containing parameters, contaminants, additives, and results. 

Created on Dec 27, 2020

@author: Wmelende
'''

def delete_concatenated_columns(df, col_names):
    '''
    Name
    ----
    delete_concatenated_columns
    
    Description
    -----------
    This function deletes all the columns containing concatenated fields of parameters, contaminants,
    additives, and results.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the inVitro data.
    nrow : int
        Number of rows in DataFrame df.
    col_names  : list
        The column headers in DataFrame df.
    
    Output Parameters
    -----------------
    Modified DataFrame df.
    '''
    # Extract the columns with concatenated parameter fields
    subs_parameters = "parameters"
    list_parameters_headers = [icol for icol in col_names if subs_parameters in icol]
    
    # Delete the parameters concatenated columns
    df.drop(list_parameters_headers, axis = 1, inplace = True)
    
    # Extract the columns with concatenated contaminant fields
    subs_contam = "contam"
    list_contaminants_headers = [icol for icol in col_names if subs_contam in icol]
    
    # Delete the contaminant concatenated columns
    df.drop(list_contaminants_headers, axis = 1, inplace = True)
    
    # Extract the columns with concatenated additive fields
    subs_additives = "additive"
    list_additives_headers = [icol for icol in col_names if subs_additives in icol]
    
    # Delete the additive concatenated columns
    df.drop(list_additives_headers, axis = 1, inplace = True)
    
    # Extract the columns with concatenated result fields
    subs_results = "result"
    list_results_headers = [icol for icol in col_names if subs_results in icol]
    
    # Delete the result concatenated columns
    df.drop(list_results_headers, inplace = True, axis = 1)
    
    