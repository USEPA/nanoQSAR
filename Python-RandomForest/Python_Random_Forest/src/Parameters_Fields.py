'''
Created on Nov 30, 2020

This module contains the definition of a function used to split up 
concatenated fields containing parameters. 

Functions
---------
split_parameters_fields(df, nrow, col_names)
    Splits up the concatenated parameter fields.
    
@author: Wilson Melendez
'''

def split_parameters_fields(df, nrow, col_names):
    '''
    Purpose
    -------
    This function splits up the concatenated fields containing the parameters.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the inVitro data.
    nrow : int
        Number of rows in DataFrame df.
    col_names  : int
        The column headers in DataFrame df.
    
    Output Parameters
    -----------------
    Modified DataFrame df.
    '''
    # Process the parameter fields.
    subs = "parameters"
    list_param = [icol for icol in col_names if subs in icol]
    num_param = len(list_param)
    param_set = set()
    for icol in range(0, num_param):
        if (df[list_param[icol]].isna().values.all() == True):
            continue
        for irow in range(0, nrow):            
            if (df[list_param[icol]].iloc[irow] == None):               
                continue            
            else:
                
                list_str = df[list_param[icol]].iloc[irow].split(":")
                # list_str[0] = number
                # list_str[1] = name of parameter
                # list_str[2] = amount of parameter (numeric value)
                # list_str[3] = unit of numeric value
                # list_str[4] = non-numeric string
                
                if list_str[1].strip().lower() in param_set:
                    if (list_str[0] != ''):
                        strnum = list_str[1].strip().lower() + ' num'
                        df.loc[irow, strnum] = int(list_str[0])
                        
                    if (list_str[2] != ''):
                        strvalue = list_str[1].strip().lower()
                        df.loc[irow, strvalue] = float(list_str[2])
                    
                    if (list_str[3] != ''):
                        strunits = list_str[1].strip().lower() + ' unit'
                        df.loc[irow, strunits] = list_str[3]
                        
                    if (list_str[4] != ''):
                        strnonnum = list_str[1].strip().lower() + ' nonnum'
                        df.loc[irow, strnonnum] = list_str[4]
                                    
                else:
                    param_set.add(list_str[1].strip().lower())
                    strvalue = list_str[1].strip().lower()
                    strunits = list_str[1].strip().lower() + ' unit'
                    strnum = list_str[1].strip().lower() + ' num'
                    strnonnum = list_str[1].strip().lower() + ' nonnum'
                    
                    if (list_str[0] != ''):                    
                        df.loc[irow, strnum] = int(list_str[0])
                    else:
                        df.loc[irow, strnum] = ''
                        
                    if (list_str[2] != ''):
                        df.loc[irow, strvalue] = float(list_str[2])
                    else:
                        df.loc[irow, strvalue] = ''
                                        
                    if (list_str[3] != ''):
                        df.loc[irow, strunits] = list_str[3]
                    else:
                        df.loc[irow, strunits] = ''
                        
                    if (list_str[4] != ''):
                        df.loc[irow, strnonnum] = list_str[4]
                    else:
                        df.loc[irow, strnonnum] = ''
    
