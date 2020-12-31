'''
This module contains the definition of a function used to split up 
concatenated fields containing additives. 

Created on Nov 30, 2020

Functions
---------
split_additive_fields(df, nrow, col_names)
    Splits up the concatenated additive fields.
    
@author: Wilson Melendez
'''

def split_additive_fields(df, nrow, col_names):
    '''
    Name
    ----
    split_additive_fields
    
    Description
    -----------
    This function splits up the concatenated fields containing the additives.
    
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
    # Process the additive fields
    subs2 = "additive"
    list_additives = [icol for icol in col_names if subs2 in icol]
    num_additives = len(list_additives)
    
    try:
        for icol in range(0, num_additives):
            if (df[list_additives[icol]].isna().values.all() == True):
                continue
            for irow in range(0, nrow):       
                if (df[list_additives[icol]].iloc[irow] == None):            
                    continue       
                else:            
                    list_str = df[list_additives[icol]].iloc[irow].split(":")
                    
                    # If additive name was not present, throw an exception.
                    if (list_str[1] == ''):
                        error_message = "Name is missing for additive " + list_additives[icol] + " at row " + irow
                        raise ValueError(error_message) 
                    
                    # list_str[0] = number
                    # list_str[1] = name of additive
                    # list_str[2] = amount of additive (numeric value)
                    # list_str[3] = unit of numeric value
                    
                    strvalue = list_str[1].strip().lower()
                    strunits = list_str[1].strip().lower() + ' unit'
                    strnum = list_str[1].strip().lower() + ' num' 
                    
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
    
    except ValueError as msg:
        error_message = msg + ", additive = " + list_additives[icol] + ", row = " + irow
        print(error_message)
    
    # Print message to console indicating completion of this function's task.
    print("Splitting of concatenated additive fields has completed.")