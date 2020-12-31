'''
This module contains the definition of a function used to split up 
concatenated fields containing parameters. 

Created on Nov 30, 2020

Functions
---------
split_parameters_fields(df, nrow, col_names)
    Splits up the concatenated parameter fields.
    
@author: Wilson Melendez
'''

def split_parameters_fields(df, nrow, col_names):
    '''
    Name
    ----
    split_parameters_fields
    
    Description
    -----------
    This function splits up the concatenated fields containing the parameters.
    
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
    
    Raises
    ------
    ValueError
        If no result type is found in concatenated string or casting to int/float fails.
    '''
    # Process the parameter fields.
    subs = "parameters"
    list_param = [icol for icol in col_names if subs in icol]
    num_param = len(list_param)
    
    try:
        for icol in range(0, num_param):
            if (df[list_param[icol]].isna().values.all() == True):
                continue
            for irow in range(0, nrow):            
                if (df[list_param[icol]].iloc[irow] == None):               
                    continue            
                else:
                    
                    list_str = df[list_param[icol]].iloc[irow].split(":")
                    
                    # If parameter name was not present, throw an exception.
                    if (list_str[1] == ''):
                        error_message = "Name is missing for parameter " + list_param[icol] + " at row " + irow
                        raise ValueError(error_message)   
                    
                    # list_str[0] = number
                    # list_str[1] = name of parameter
                    # list_str[2] = amount of parameter (numeric value)
                    # list_str[3] = unit of numeric value
                    # list_str[4] = non-numeric string
                    
                    strvalue = list_str[1].strip().lower()
                    strunits = list_str[1].strip().lower() + ' unit'
                    strnum = list_str[1].strip().lower() + ' num'
                    strnonnum = list_str[1].strip().lower() + ' nonnum'
                    
                    if (list_str[0] != ''):                    
                        df.loc[irow, strnum] = int(list_str[0])
                    else:
                        df.loc[irow, strnum] = None
                            
                    if (list_str[2] != ''):
                        df.loc[irow, strvalue] = float(list_str[2])
                    else:
                        df.loc[irow, strvalue] = None
                                            
                    if (list_str[3] != ''):
                        df.loc[irow, strunits] = list_str[3]
                    else:
                        df.loc[irow, strunits] = None
                            
                    if (list_str[4] != ''):
                        df.loc[irow, strnonnum] = list_str[4]
                    else:
                        df.loc[irow, strnonnum] = None
    
    except ValueError as msg:
        error_message = msg + ", parameter = " + list_param[icol] + ", row = " + irow
        print(error_message)
        
    # Print message to console indicating completion of this function's task.
    print("Splitting of concatenated parameter fields has completed.")

    