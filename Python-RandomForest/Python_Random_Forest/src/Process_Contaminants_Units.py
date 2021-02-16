'''
This module contains the definition of a function that 
processes the units for the contaminants. 

Created on Feb 15, 2021

@author: Wmelende
'''
import math
 
def process_contaminants_units(df):
    '''
    Name
    ----
    process_contaminants_units
    
    Description
    -----------
    This function processes the contaminants' units.
    
    Input Parameters
    ----------------
    df : DataFrame
        DataFrame containing the inVitro data.
    
    Output Parameters
    -----------------
    Modified DataFrame df.
    
    Raises
    ------
    ValueError
        If units are unknown or incompatible.
    '''   
    # Determine number of rows in data frame.
    nrow = len(df.index)
    
    # ########################################################################################################
    # Process contaminant units
    # ########################################################################################################
    
    # Process cobalt units
    col_value = "cobalt contaminant_value"
    col_units = "cobalt contaminant_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'parts per million' or 
                    str_units == 'parts per billion'):
                    if (str_units == 'parts per billion'):
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process potassium units
    col_value = "potassium contaminant_value"
    col_units = "potassium contaminant_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'parts per million' or 
                    str_units == 'parts per billion'):
                    if (str_units == 'parts per billion'):
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process silicon dioxide units
    col_value = "silicon dioxide contaminant_value"
    col_units = "silicon dioxide contaminant_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'parts per million' or 
                    str_units == 'parts per billion'):
                    if (str_units == 'parts per billion'):
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process vanadium units
    col_value = "vanadium contaminant_value"
    col_units = "vanadium contaminant_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'parts per million' or 
                    str_units == 'parts per billion'):
                    if (str_units == 'parts per billion'):
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  