'''
Created on Nov 30, 2020

This module contains the definition of a function used to split up 
concatenated fields containing contaminants. 

Functions
---------
split_contaminant_fields(df, nrow, col_names)
    Splits up the concatenated contaminant fields.

@author: Wilson Melendez
'''

def split_contaminant_fields(df, nrow, col_names):
    '''
    Purpose
    -------
    This function splits up the concatenated fields containing the contaminants.
    
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
    # Process the contaminant fields     
    subs1 = "contam"
    list_contaminants = [icol for icol in col_names if subs1 in icol]
    num_contaminants = len(list_contaminants)
    contaminants_set = set()
    for icol in range(0, num_contaminants):
        if (df[list_contaminants[icol]].isna().values.all() == True):
            continue
        for irow in range(0, nrow):       
            if (df[list_contaminants[icol]].iloc[irow] == None):            
                continue       
            else:         
                list_str = df[list_contaminants[icol]].iloc[irow].split(":")  
                # list_str[0] = number
                # list_str[1] = name of contaminant
                # list_str[2] = amount of contaminant (numeric value)
                # list_str[3] = unit of numeric value
                # list_str[4] = method used       
                if list_str[1].strip().lower() in contaminants_set:
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
                        strnonnum = list_str[1].strip().lower() + ' method'
                        df.loc[irow, strnonnum] = list_str[4]
                                    
                else:
                    contaminants_set.add(list_str[1].strip().lower())
                    strvalue = list_str[1].strip().lower()
                    strunits = list_str[1].strip().lower() + ' unit'
                    strnum = list_str[1].strip().lower() + ' num'
                    strnonnum = list_str[1].strip().lower() + ' method'
                    
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
