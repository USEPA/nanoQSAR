'''
Created on Nov 30, 2020

This module contains the definition of a function used to split up 
concatenated fields containing additives. 

Functions
---------
split_additive_fields(df, nrow, col_names)
    Splits up the concatenated additive fields.
    
@author: Wilson Melendez
'''

def split_additive_fields(df, nrow, col_names):
    '''
    Purpose
    -------
    This function splits up the concatenated fields containing the additives.
    
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
    # Process the additive fields
    subs2 = "additive"
    list_additives = [icol for icol in col_names if subs2 in icol]
    num_additives = len(list_additives)
    additives_set = set()
    for icol in range(0, num_additives):
        if (df[list_additives[icol]].isna().values.all() == True):
            continue
        for irow in range(0, nrow):       
            if (df[list_additives[icol]].iloc[irow] == None):            
                continue       
            else:            
                list_str = df[list_additives[icol]].iloc[irow].split(":")
                # list_str[0] = number
                # list_str[1] = name of additive
                # list_str[2] = amount of additive (numeric value)
                # list_str[3] = unit of numeric value
                            
                if list_str[1].strip().lower() in additives_set:
                    if (list_str[0] != ''):
                        strnum = list_str[1].strip().lower() + ' num'
                        df.loc[irow, strnum] = int(list_str[0])
                        
                    if (list_str[2] != ''):
                        strvalue = list_str[1].strip().lower()
                        df.loc[irow, strvalue] = float(list_str[2])
                    
                    if (list_str[3] != ''):
                        strunits = list_str[1].strip().lower() + ' unit'
                        df.loc[irow, strunits] = list_str[3]
                                    
                else:
                    additives_set.add(list_str[1].strip().lower())
                    strvalue = list_str[1].strip().lower()
                    strunits = list_str[1].strip().lower() + ' unit'
                    strnum = list_str[1].strip().lower() + ' num'
                    
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
    