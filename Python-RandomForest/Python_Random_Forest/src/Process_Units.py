'''
Created on Jan 10, 2021

@author: Wmelende
'''
import math

def process_data_units(df):
    
    col_names = list(df.columns)
    
    # Determine number of rows in data frame.
    nrow = len(df.index)
    
    # Process Outer Diameter units
    col_value = "OuterDiameterValue"
    col_units = "OuterDiameterUnit"
    col_low = "OuterDiameterLow"
    col_high = "OuterDiameterHigh"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                if (df[col_units].iloc[irow] == 'nanometers' or df[col_units].iloc[irow] == 'micrometers'):
                    if (df[col_units].iloc[irow] == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers'
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1000
                        if (col_low in col_names):
                            if (df[col_low].iloc[irow] != None):
                                df.loc[irow, col_low] = df.loc[irow, col_low] * 1000
                        if (col_high in col_names):   
                            if (df[col_high].iloc[irow] != None):
                                df.loc[irow, col_high] = df.loc[irow, col_high] * 1000                        
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)
        
        
    # Process Thickness units
    col_value = "ThicknessValue"
    col_units = "ThicknessUnit"
    col_low = "ThicknessLow"
    col_high = "ThicknessHigh"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    # df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                if (df[col_units].iloc[irow] == 'nanometers' or df[col_units].iloc[irow] == 'micrometers'):
                    if (df[col_units].iloc[irow] == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers'
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1000
                        if (col_low in col_names):
                            if (df[col_low].iloc[irow] != None):
                                df.loc[irow, col_low] = df.loc[irow, col_low] * 1000
                        if (col_high in col_names):   
                            if (df[col_high].iloc[irow] != None):
                                df.loc[irow, col_high] = df.loc[irow, col_high] * 1000                        
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
                
    
    # Process SurfaceArea units
    # The units of cubed meters/gram and milligrams/gram are not compatible with surface area.
    col_value = "SurfaceAreaValue"
    col_units = "SurfaceAreaUnit"
    col_low = "SurfaceAreaLow"
    col_high = "SurfaceAreaHigh"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                if (df[col_units].iloc[irow] == 'square meters/gram' or 
                    df[col_units].iloc[irow] == 'square centimeters/gram' or 
                    df[col_units].iloc[irow] == 'square meter/gram' or 
                    df[col_units].iloc[irow] == 'cubed meters/gram' or 
                    df[col_units].iloc[irow] == 'milligrams/gram'):
                    if (df[col_units].iloc[irow] == 'square meter/gram'):
                        df.loc[irow, col_units] = 'square meters/gram'
                    elif (df[col_units].iloc[irow] == 'square centimeters/gram'):
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 10E-4
                        if (col_low in col_names):
                            if (df[col_low].iloc[irow] != None):
                                df.loc[irow, col_low] = df.loc[irow, col_low] * 10E-4
                        if (col_high in col_names):   
                            if (df[col_high].iloc[irow] != None):
                                df.loc[irow, col_high] = df.loc[irow, col_high] * 10E-4
                    elif (df[col_units].iloc[irow] == 'cubed meters/gram'): 
                        df.loc[irow, col_value] = None
                        df.loc[irow, col_units] = None
                        if (col_low in col_names):
                            df.loc[irow, col_low] = None
                        if (col_high in col_names):
                            df.loc[irow, col_high] = None
                    elif (df[col_units].iloc[irow] == 'milligrams/gram'): 
                        df.loc[irow, col_value] = None
                        df.loc[irow, col_units] = None
                        if (col_low in col_names):
                            df.loc[irow, col_low] = None
                        if (col_high in col_names):
                            df.loc[irow, col_high] = None
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process Purity units
    col_value = "Purity"
    col_units = "PurityUnit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                if (df[col_units].iloc[irow] == 'percent' or df[col_units].iloc[irow] == 'fraction'):
                    if (df[col_units].iloc[irow] == 'fraction'):
                        df.loc[irow, col_units] = 'percent'
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 100                     
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)
        
    # Process Hydrodynamic Diameter units
    col_value = "HydrodynamicDiameterValue"
    col_units = "HydrodynamicDiameterUnit"
    col_low = "HydrodynamicDiameterLow"
    col_high = "HydrodynamicDiameterHigh"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    #df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                if (df[col_units].iloc[irow] == 'nanometers' or df[col_units].iloc[irow] == 'micrometers'):
                    if (df[col_units].iloc[irow] == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers'
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1000
                        if (col_low in col_names):
                            if (df[col_low].iloc[irow] != None):
                                df.loc[irow, col_low] = df.loc[irow, col_low] * 1000
                        if (col_high in col_names):   
                            if (df[col_high].iloc[irow] != None):
                                df.loc[irow, col_high] = df.loc[irow, col_high] * 1000                        
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process Charge units
    col_value = "ChargeAvg"
    col_units = "ChargeUnit"
    col_low = "ChargeLow"
    col_high = "ChargeHigh"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    #df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    #df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                if (df[col_units].iloc[irow] == 'millivolts' or df[col_units].iloc[irow] == 'volts'):
                    if (df[col_units].iloc[irow] == 'volts'):
                        df.loc[irow, col_units] = 'millivolts'
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1000
                        if (col_low in col_names):
                            if (df[col_low].iloc[irow] != None):
                                df.loc[irow, col_low] = df.loc[irow, col_low] * 1000
                        if (col_high in col_names):   
                            if (df[col_high].iloc[irow] != None):
                                df.loc[irow, col_high] = df.loc[irow, col_high] * 1000                        
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
     
    ########################################################################################################## 
    # Process parameter Units
    # 1 ppm = approximately 1 mg/L = 1 ug/mL
    # The inverse of log(x) = y is x = 10**y
    ##########################################################################################################
    # Process Particle Concentration units
    # The units of milligrams and micrograms are not compatible with concentration units.
    # The unit of micromolar is a concentration unit but we must know the molar weight of the 
    # particle/chemical in question.
    col_value = "particle concentration parameter_value"
    col_units = "particle concentration parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/liter' or
                    str_units == 'parts per million' or 
                    str_units == 'parts per billion' or
                    str_units == 'micrograms/cubed meter' or 
                    str_units == 'milligrams' or 
                    str_units == 'micrograms' or 
                    str_units == 'micromolar' or 
                    str_units == 'log of micrograms/milliliter'):
                    if (str_units == 'micrograms/milliliter' or str_units == 'milligrams/liter' ):
                        df.loc[irow, col_units] = 'parts per million' 
                    elif (str_units == 'parts per billion'):
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 10E-3
                    elif (str_units == 'micrograms/cubed meter'):
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 10E-6
                    elif (str_units == 'log of micrograms/milliliter'):
                        print(df.loc[irow, col_value])
                        df.loc[irow, col_units] = 'parts per million' 
                        df.loc[irow, col_value] = 10**df.loc[irow, col_value] 
                    elif(str_units == 'milligrams' or str_units == 'micrograms' or str_units == 'micromolar'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process Particle Concentration Log units
    col_value = "particle concentration log parameter_value"
    col_units = "particle concentration log parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/liter' or
                    str_units == 'parts per million' or 
                    str_units == 'parts per billion' or
                    str_units == 'micrograms/cubed meter'):
                    if (str_units == 'milligrams/liter' or str_units == 'parts per million'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = 10**df.loc[irow, col_value]
                    elif (str_units == 'parts per billion'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = 10**df.loc[irow, col_value] * 10E-3
                    elif (str_units == 'micrograms/cubed meter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = 10**df.loc[irow, col_value] * 10E-6
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    
    # Process Particle Mass units
    col_value = "particle mass parameter_value"
    col_units = "particle mass parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms' or 
                    str_units == 'milligrams'):
                    if (str_units == 'milligrams'):
                        df.loc[irow, col_units] = 'micrograms' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+3
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    
    # Process Irradiance Power units
    col_value = "irradiance power parameter_value"
    col_units = "irradiance power parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'microwatts/square centimeter' or 
                    str_units == 'milliwatts/square centimeter'):
                    if (str_units == 'milliwatts/square centimeter'):
                        df.loc[irow, col_units] = 'microwatts/square centimeter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+3
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process Duration Incubation units
    col_value = "duration incubation parameter_value"
    col_units = "duration incubation parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'hours' or 
                    str_units == 'minutes'):
                    if (str_units == 'minutes'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 60
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " 
                                     + str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process concentration silver units
    col_value = "concentration silver nitrate parameter_value"
    col_units = "concentration silver nitrate parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/milliliter'):
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " 
                                     + str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)      
        
    # Process PH units
    col_value = "ph parameter_value"
    col_units = "ph parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'pH' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'pH' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " 
                                     + str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process measured zinc sulfate concentration units
    col_value = "measured zinc sulfate concentration parameter_value"
    col_units = "measured zinc sulfate concentration parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " +
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)      
    
    # Process measured material concentration units
    col_value = "measured material concentration parameter_value"
    col_units = "measured material concentration parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)      
    
    # Process concentration sodium chloride units
    col_value = "concentration sodium chloride parameter_value"
    col_units = "concentration sodium chloride parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'molar'):
                    if (str_units == 'molar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process concentration calcium chloride units
    col_value = "concentration calcium chloride parameter_value"
    col_units = "concentration calcium chloride parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'molar'):
                    if (str_units == 'molar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process time of measurement units
    col_value = "time of measurement parameter_value"
    col_units = "time of measurement parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'days' or str_units == 'hours' or str_units == 'minutes'):
                    if (str_units == 'days'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 24
                    elif (str_units == 'minutes'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 60
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process hydrodynamic diameter units
    col_value = "hydrodynamic diameter parameter_value"
    col_units = "hydrodynamic diameter parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanometers' or 
                    str_units == 'micrometers'):
                    if (str_units == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process duration exposure units
    col_value = "duration exposure parameter_value"
    col_units = "duration exposure parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'weeks' or str_units == 'days' or str_units == 'hours' or str_units == 'minutes'):
                    if (str_units == 'weeks'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 7 * 24
                    elif (str_units == 'days'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 24
                    elif (str_units == 'minutes'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 60
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)       
    
    # Process duration aging units
    col_value = "duration aging parameter_value"
    col_units = "duration aging parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'days' or str_units == 'hours' or str_units == 'minutes'):
                    if (str_units == 'hours'):
                        df.loc[irow, col_units] = 'days' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 24
                    elif(str_units == 'minutes'):
                        df.loc[irow, col_units] = 'days' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / (24 * 60)
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process duration irradiation units
    col_value = "duration irradiation parameter_value"
    col_units = "duration irradiation parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'hour' or str_units == 'hours' or str_units == 'minutes'):
                    if (str_units == 'hour'):
                        df.loc[irow, col_units] = 'hours' 
                    elif(str_units == 'minutes'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 60
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process temperature units
    col_value = "temperature parameter_value"
    col_units = "temperature parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'celsius' or str_units == 'Fahrenheit'):
                    if (str_units == 'Fahrenheit'):
                        df.loc[irow, col_units] = 'celsius' 
                        df.loc[irow, col_value] = (df[col_value].iloc[irow] - 32.0) * 5 / 9    
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process concentration zinc units
    col_value = "concentration zinc parameter_value"
    col_units = "concentration zinc parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/milliliter'):
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process concentration cadmium sulfate units
    col_value = "concentration cadmium sulfate parameter_value"
    col_units = "concentration cadmium sulfate parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
        
    # Process duration dissolution units
    col_value = "duration dissolution parameter_value"
    col_units = "duration dissolution parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'hours' or str_units == 'minutes'):
                    if(str_units == 'minutes'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 60
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message) 
    
    # Process concentration copper units
    col_value = "concentration copper parameter_value"
    col_units = "concentration copper parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/milliliter'):
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
        
    # Process concentration copper units
    col_value = "concentration suwannee river natural organic matter parameter_value"
    col_units = "concentration suwannee river natural organic matter parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'milligrams/liter' or 
                    str_units == 'micrograms/liter'):
                    if (str_units == 'micrograms/liter'):
                        df.loc[irow, col_units] = 'milligrams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process concentration copper units
    col_value = "concentration lutein parameter_value"
    col_units = "concentration lutein parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message) 
    
    # Process concentration dehydroascorbic acid units
    col_value = "concentration dehydroascorbic acid parameter_value"
    col_units = "concentration dehydroascorbic acid parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)       
    
    # Process concentration ascorbic acid 6-palmitate units
    col_value = "concentration ascorbic acid 6-palmitate parameter_value"
    col_units = "concentration ascorbic acid 6-palmitate parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)      
    
    # Process irradiance contaminant units
    col_value = "irradiance parameter_value"
    col_units = "irradiance parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'joules/square centimeter' or 
                    str_units == 'joules/square meter'):
                    if (str_units == 'joules/square meter'):
                        df.loc[irow, col_units] = 'joules/square centimeter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-04
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
    
    # Process concentration poly l-lysine units
    col_value = "concentration poly l-lysine parameter_value"
    col_units = "concentration poly l-lysine parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/milliliter'):
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
    
    # Process oxygen units
    col_value = "oxygen parameter_value"
    col_units = "oxygen parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
    
    # Process laser wavelength units
    col_value = "laser wavelength parameter_value"
    col_units = "laser wavelength parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanometers' or 
                    str_units == 'micrometers'):
                    if (str_units == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
    
    # Process duration lutein exposure units
    col_value = "duration lutein exposure parameter_value"
    col_units = "duration lutein exposure parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'hours' or 
                    str_units == 'days'):
                    if (str_units == 'days'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 24
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
    
    # Process duration lutein exposure units
    col_value = "duration dehydroascorbic acid exposure parameter_value"
    col_units = "duration dehydroascorbic acid exposure parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'hours' or 
                    str_units == 'days'):
                    if (str_units == 'days'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 24
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)        
    
    # Process duration ascorbic acid 6-palmitate exposure units
    col_value = "duration ascorbic acid 6-palmitate exposure parameter_value"
    col_units = "duration ascorbic acid 6-palmitate exposure parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'hours' or 
                    str_units == 'days'):
                    if (str_units == 'days'):
                        df.loc[irow, col_units] = 'hours' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 24
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)       
    
    # Process irradiation power units
    col_value = "irradiation power parameter_value"
    col_units = "irradiation power parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'joules/square meter' or 
                    str_units == 'joules/square centimeter'):
                    if (str_units == 'joules/square centimeter'):
                        df.loc[irow, col_units] = 'joules/square meter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+04
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
               
    # Process number of cells units
    col_value = "number of cells parameter_value"
    col_units = "number of cells parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'cells' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'cells'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)            
    
    # Process bicucullin dose contaminant units
    col_value = "bicucullin dose parameter_value"
    col_units = "bicucullin dose parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process concentration carbon dioxide units
    col_value = "concentration carbon dioxide parameter_value"
    col_units = "concentration carbon dioxide parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)       
        
    # Process dose contaminant units
    col_value = "dose parameter_value"
    col_units = "dose parameter_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
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
    
    # #######################################################################################################
    #  Process additive units
    # #######################################################################################################
    
    # Process fetal bovine serum units
    # The number '5' is not a unit.
    col_value = "fetal bovine serum additive_value"
    col_units = "fetal bovine serum additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == '5'):
                    if (str_units == '5'):
                        df.loc[irow, col_units] = 'percent'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process bovine serum albumin units
    col_value = "bovine serum albumin additive_value"
    col_units = "bovine serum albumin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process deionized water units
    col_value = "deionized water additive_value"
    col_units = "deionized water additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process potassium chloride units
    # Chemical formula: KCl
    # molar mass = 74.5513 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "potassium chloride additive_value"
    col_units = "potassium chloride additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'molar' or 
                    str_units == 'grams/liter'):
                    if (str_units == 'molar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif(str_units == 'grams/liter'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03 / 74.5513
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process phosphate buffer units
    # Why is there an 'unspecified' unit?
    col_value = "phosphate buffer additive_value"
    col_units = "phosphate buffer additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'unspeficied'):
                    if (str_units == 'unspeficied'):
                        df.loc[irow, col_units] = None 
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process trichloroacetic acid units
    col_value = "trichloroacetic acid additive_value"
    col_units = "trichloroacetic acid additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'molar'):
                    if (str_units == 'molar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process sodium chloride units
    # Chemical formula: NaCl
    # molar mass = 58.44 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "sodium chloride additive_value"
    col_units = "sodium chloride additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'molar' or 
                    str_units == 'grams/liter' or 
                    str_units == "milligrams/liter" or 
                    str_units == "millimoles"):
                    if (str_units == 'molar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif(str_units == 'grams/liter'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03 / 58.44
                    elif(str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 58.44
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
       
    # Process 2',7'dichlorohydrofluorescein diacetate units
    col_value = "2',7'dichlorohydrofluorescein diacetate additive_value"
    col_units = "2',7'dichlorohydrofluorescein diacetate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar' or 
                    str_units == 'millimolar'):
                    if (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micromolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process water units
    col_value = "water additive_value"
    col_units = "water additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process dimethyl sulfoxide units
    col_value = "dimethyl sulfoxide additive_value"
    col_units = "dimethyl sulfoxide additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process calcium chloride units
    # Chemical formula: CaCl_2
    # molar mass = 110.98 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "calcium chloride additive_value"
    col_units = "calcium chloride additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or
                    str_units == 'molar' or
                    str_units == 'grams/liter' or 
                    str_units == "milligrams/liter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'molar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif(str_units == 'grams/liter'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03 / 110.98
                    elif(str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] / 110.98
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process calcium sulfate units
    # Chemical formula: CaSO4
    # molar mass = 136.14 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "calcium sulfate additive_value"
    col_units = "calcium sulfate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'grams/liter' or                     
                    str_units == "milligrams/liter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'grams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process glucose units
    col_value = "glucose additive_value"
    col_units = "glucose additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'grams/liter' or                     
                    str_units == "milligrams/liter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'grams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process gentamicin units
    col_value = "gentamicin additive_value"
    col_units = "gentamicin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or                     
                    str_units == "milligrams/milliliter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process l-cysteine units
    # 
    col_value = "l-cysteine additive_value"
    col_units = "l-cysteine additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'milligrams/milliliter' or                     
                    str_units == "micrograms/milliliter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'micrograms/milliliter'):
                        df.loc[irow, col_units] = 'milligrams/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process ethylenediaminetetraacetic acid units
    col_value = "ethylenediaminetetraacetic acid additive_value"
    col_units = "ethylenediaminetetraacetic acid additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or 
                    str_units == 'micromolar'):
                    if (str_units == 'micromolar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process penicillin units
    # Penicillin, with double ll, is the correct spelling.
    col_value = "penicillin additive_value"
    col_units = "penicillin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'units/milliliter' or 
                    str_units == 'units/milliliters'):
                    if (str_units == 'units/milliliters'):
                        df.loc[irow, col_units] = 'units/milliliter' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process hank's balanced salt solution units
    col_value = "hank's balanced salt solution additive_value"
    col_units = "hank's balanced salt solution additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process citrate buffer units
    col_value = "citrate buffer additive_value"
    col_units = "citrate buffer additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process sodium bromide units
    # Chemical formula: NaBr
    # molar mass = 102.894 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "sodium bromide additive_value"
    col_units = "sodium bromide additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'milligrams/liter' or                     
                    str_units == "grams/liter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'grams/liter'):
                        df.loc[irow, col_units] = 'milligrams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process l-glutamine units
    # Chemical formula: C5H10N2O3
    # molar mass = 146.146 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "l-glutamine additive_value"
    col_units = "l-glutamine additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimolar' or                     
                    str_units == "micromolar" or 
                    str_units == "millimole"):                     
                    if (str_units == 'micromolar'):
                        df.loc[irow, col_units] = 'millimolar' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                    elif(str_units == 'millimole'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process penicillin / streptomycin units
    col_value = "penicillin / streptomycin additive_value"
    col_units = "penicillin / streptomycin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 100
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process 1,2-dipalmitoyl-sn-glycero-3-phosphocholine units
    col_value = "1,2-dipalmitoyl-sn-glycero-3-phosphocholine additive_value"
    col_units = "1,2-dipalmitoyl-sn-glycero-3-phosphocholine additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 100
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process sodium pyruvate additive_unit units
    col_value = "sodium pyruvate additive_value"
    col_units = "sodium pyruvate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimole' or 
                    str_units == 'micromole'):
                    if (str_units == 'micromole'):
                        df.loc[irow, col_units] = 'millimole' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process amphotericin b units
    # Chemical formula: C47H73NO17
    # molar mass = 924.091 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "amphotericin b additive_value"
    col_units = "amphotericin b additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or                     
                    str_units == "milligrams/milliliter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process trypan units
    col_value = "trypan additive_value"
    col_units = "trypan additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 100
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process thymidine units
    # Chemical formula: C10H14N2O5
    # molar mass = 242.231 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "thymidine additive_value"
    col_units = "thymidine additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or  
                    str_units == 'millimolar' or
                    str_units == "milligrams/milliliter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 242.231 
                    elif (str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process streptomycin units
    # Chemical formula: C21H39N7O12
    # molar mass = 581.580 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "streptomycin additive_value"
    col_units = "streptomycin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or                     
                    str_units == "milligrams/milliliter" or 
                    str_units == "millimolar" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif (str_units == "millimolar"):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 581.580
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process magnesium sulfate units
    # Chemical formula: MgSO4
    # molar mass = 120.366 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "magnesium sulfate additive_value"
    col_units = "magnesium sulfate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'grams/liter' or                     
                    str_units == 'milligrams/liter' or 
                    str_units == 'millimolar' or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'grams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                    elif (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'grams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 120.366 * 1.0E+03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process sodium bicarbonate units
    # Chemical formula: NaHCO3
    # molar mass = 84.007 g/mol
    # 1 molar = 1 mol/liter
    # 1 molar = 1000 * millimolar
    # 1 millimolar = 1.0E-3 * molar
    col_value = "sodium bicarbonate additive_value"
    col_units = "sodium bicarbonate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'grams/liter' or   
                    str_units == 'millimolar' or  
                    str_units == "milligrams/liter" or 
                    str_units == "millimoles"):                     
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'grams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                    elif (str_units == 'millimolar'):
                        df.loc[irow, col_units] = 'grams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 84.007 * 1.0E+03
                    elif(str_units == 'millimoles'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process ferric nitrate units
    col_value = "ferric nitrate additive_value"
    col_units = "ferric nitrate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'milligrams/milliliter' or 
                    str_units == 'micrograms/milliliter'):
                    if (str_units == 'micrograms/milliliter'):
                        df.loc[irow, col_units] = 'milligrams/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process steptomycin units
    col_value = "steptomycin additive_value"
    col_units = "steptomycin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'milligrams/milliliter'):
                    if (str_units == 'milligrams/milliliter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
       
    # Process phosphate units
    col_value = "phosphate additive_value"
    col_units = "phosphate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millimoles' or 
                    str_units == 'micromoles'):
                    if (str_units == 'micromoles'):
                        df.loc[irow, col_units] = 'millimoles' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # Process potassium bisulfate units
    col_value = "potassium bisulfate additive_value"
    col_units = "potassium bisulfate additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'normality' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'normality' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process penicilin units
    # Spelling is wrong.
    col_value = "penicilin additive_value"
    col_units = "penicilin additive_unit"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'enzyme units/milliliter' or 
                    str_units == 'enzyme units/liter'):
                    if (str_units == 'enzyme units/liter'):
                        df.loc[irow, col_units] = 'enzyme units/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
    
    # ######################################################################################################
    # Process units of results
    # ######################################################################################################
    col_names = list(df.columns)
    subs_low = "result_low"
    subs_high = "result_high"
    list_result_low = [icol for icol in col_names if subs_low in icol]
    list_result_high = [icol for icol in col_names if subs_high in icol]
    
    # Process front scatter units
    result_type = "front scatter"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process fluorescence units
    # The fluorescence data appears to consist of 3 different data groups that are not comparable 
    # to one another.  Their data ranges are very different.
    result_type = "fluorescence"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow].lower()
                if (str_units == 'ratio to control' or 
                    str_units == 'relative fluorescence units' or 
                    str_units == 'fluorescence intensity'):
                    if (str_units == 'relative light units'):
                        df.loc[irow, col_units] = 'relative fluorescence units' 
                    elif (str_units == 'ratio to control' or str_units == 'Fluorescence Intensity'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process  units
    col_value = "side scatter result_value"
    col_units = "side scatter result_unit"
    col_low = " result_low"
    col_high = " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'ratio to control'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process dna damage units
    result_type = "dna damage"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process number of micronuclei  units
    result_type = "number of micronuclei"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micronuclei' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'micronuclei' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process hydrodynamic diameter units
    result_type = "hydrodynamic diameter"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanometers' or 
                    str_units == 'micrometers'):
                    if (str_units == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process nfkb activation units
    result_type = "nfkb activation"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
        
    # Process ap1 activation units
    result_type = "ap1 activation"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process concentration titanium dioxide units
    result_type = "concentration titanium dioxide"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'milligrams/liter' or 
                    str_units == 'micrograms/liter'):
                    if (str_units == 'micrograms/liter'):
                        df.loc[irow, col_units] = 'milligrams/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E-03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E-03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process growth rate units
    result_type = "growth rate"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanometers' or 
                    str_units == 'micrometers'):
                    if (str_units == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process genomics units
    result_type = "genomics"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process alanine transaminase activity units
    result_type = "alanine transaminase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process aspartate transaminase activity units
    result_type = "aspartate transaminase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process lactate dehydrogenase activity units
    # Not sure how the units should be converted for this case.
    # The units 'ratio' and 'percent' are ambiguous because we don't know what they are 
    # measured in relation to.  Unfortunately, all 3 units appear to be incompatible to 
    # one another: their data ranges are quite different.
    result_type = "lactate dehydrogenase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'percent' or 
                    str_units == 'enzyme units/liter'):
                    if (str_units == 'percent' or str_units == 'ratio'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process alkaline phosphatase activity units
    result_type = "alkaline phosphatase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process chloramphenicol acetyltransferase activity units
    result_type = "chloramphenicol acetyltransferase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process glucose-6-phosphate dehydrogenase activity units
    result_type = "glucose-6-phosphate dehydrogenase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process gamma-glutamyl transferase activity units
    result_type = "gamma-glutamyl transferase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process glutathione peroxidase activity units
    result_type = "glutathione peroxidase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process glutathione reductase activity units
    result_type = "glutathione reductase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process glutathione activity units
    # Not sure how to convert these units.
    # Values with Relative Light units are much higher than those with ratio and 
    # Chemiluminescence units. The units reported are not compatible to one another.
    # Strictly speaking, Chemiluminescence units and Relative Light Units are not units of
    # glutathione activity.  Ratio would be the best unit choice in this case.
    result_type = "glutathione activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'Relative Light Units' or 
                    str_units == 'Chemiluminescence units'):
                    if (str_units == 'Chemiluminescence units' or str_units == 'Relative Light Units'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process microalbulmin activity units
    result_type = "microalbulmin activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process released protein units
    result_type = "released protein"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process superoxide dismutase activity units
    result_type = "superoxide dismutase activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)  
        
    # Process released bilirubin units
    result_type = "released bilirubin"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
        
    # Process released thioredoxin reductase units
    result_type = "released thioredoxin reductase"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process released triglycerides units
    result_type = "released triglycerides"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process size units
    # Two of the units are incompatible.
    result_type = "size"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanometers' or 
                    str_units == 'micrometers' or 
                    str_units == 'micrograms/liter' or 
                    str_units == 'percent'):
                    if (str_units == 'micrometers'):
                        df.loc[irow, col_units] = 'nanometers' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                    elif (str_units == 'percent' or str_units == 'micrograms/liter'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
        
    # Process zeta potential units
    result_type = "zeta potential"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'millivolts' or 
                    str_units == 'volts'):
                    if (str_units == 'volts'):
                        df.loc[irow, col_units] = 'millivolts' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E-03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E-03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process concentration silver units
    # Two of the units are not considered concentration units.
    result_type = "concentration silver"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/kilogram' or 
                    str_units == 'nanometers'):
                    if (str_units == 'micrograms/liter'):
                        df.loc[irow, col_units] = 'micrograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E-03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E-03 
                    elif (str_units == 'milligrams/kilogram' or str_units == 'nanometers'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process cell activity units
    result_type = "cell activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process deposition units
    result_type = "deposition"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 100
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 100
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 100
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
        
    # Process mass silver  units
    result_type = "mass silver"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms' or 
                    str_units == 'milligrams'):
                    if (str_units == 'milligrams'):
                        df.loc[irow, col_units] = 'micrograms' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process caspase 3/7 activity units
    result_type = "caspase 3/7 activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'Chemiluminescence units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)    
    
    # Process concentration nitrate units
    # Are the units correct??
    result_type = "concentration nitrate"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'Chemiluminescence units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process total protein units
    result_type = "total protein"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms' or 
                    str_units == 'milligrams'):
                    if (str_units == 'milligrams'):
                        df.loc[irow, col_units] = 'micrograms' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process reactive oxygen species units
    # Not sure how to convert micromolar to fold activity or viceversa.
    result_type = "reactive oxygen species"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'micromolar'):
                    if (str_units == 'mmicromolar'):
                        df.loc[irow, col_units] = None
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process fold increase units
    result_type = "fold increase"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process absorbance units
    result_type = "absorbance"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'absorbance units' or 
                    str_units == None):
                    if (str_units == None):
                        df.loc[irow, col_units] = 'absorbance units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process metabolically active cells units
    result_type = "metabolically active cells"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process apoptosis units
    result_type = "apoptosis"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process caspase-3 activity units
    result_type = "caspase-3 activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micromolar/(hour*milligram)' or 
                    str_units == 'millimolar/(hour*milligram)'):
                    if (str_units == 'millimolar/(hour*milligram)'):
                        df.loc[irow, col_units] = 'micromolar/(hour*milligram)' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process viability units
    result_type = "viability"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process lethal concentration 50 percent units
    # This result has nanometers as one of the its units but this has to be wrong. 
    result_type = "lethal concentration 50 percent"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/milliliter' or 
                    str_units == 'nanometers'):
                    if (str_units == 'nanometers'):
                        df.loc[irow, col_units] = None 
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process nrf2/are units
    result_type = "nrf2/are"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process ap1 units
    result_type = "ap1"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process nfkb units
    result_type = "nfkb"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process glutathion units
    result_type = "glutathion"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process relative fluorescence units
    # How is ratio defined?
    result_type = "relative fluorescence"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Relative fluorescence units' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = None 
                        df.loc[irow, col_value] = None
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = None
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = None 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process change in active electrodes units
    result_type = "change in active electrodes"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == None):
                    if (str_units == None):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process electrophoretic mobility units
    result_type = "electrophoretic mobility"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrometers*centimeters/(volt*second)' or 
                    str_units == 'millimeters*centimeters/(volt*second)'):
                    if (str_units == 'millimeters*centimeters/(volt*second)'):
                        df.loc[irow, col_units] = 'micrometers*centimeters/(volt*second)' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process deposition rate units
    # Hertz is the standard unit for frequency.
    # One Hertz = one cycle per second
    # One microhertz = 1.0E-06 Hertz
    result_type = "deposition rate"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Hertz/second' or 
                    str_units == '10^-6/second'):
                    if (str_units == '10^-6/second'):
                        df.loc[irow, col_units] = 'Hertz/second' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E-06
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E-06
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E-06 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process produced biogas units
    result_type = "produced biogas"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'milliliters' or 
                    str_units == 'liters'):
                    if (str_units == 'liters'):
                        df.loc[irow, col_units] = 'milliliters' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process concentration dissolved zinc ions units
    result_type = "concentration dissolved zinc ions"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process initial deposition rate units
    result_type = "initial deposition rate"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Hertz/minute' or 
                    str_units == 'Hertz/second'):
                    if (str_units == 'Hertz/second'):
                        df.loc[irow, col_units] = 'Hertz/minute' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 60
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 60
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 60 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process dissipation to frequency shift ratio units
    result_type = "dissipation to frequency shift ratio"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process induced dna damage units
    result_type = "induced dna damage"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process micronuclei/1000 binucleated cells units
    result_type = "micronuclei/1000 binucleated cells"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process resistance units
    result_type = "resistance"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process chemiluminescence units
    result_type = "chemiluminescence"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'Chemiluminescence'):
                    if (str_units == 'Chemiluminescence'):
                        df.loc[irow, col_units] = 'Chemiluminescence units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process transcellular electrical resistance units
    result_type = "transcellular electrical resistance"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process ap-1 activity units
    result_type = "ap-1 activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'Chemiluminescence'):
                    if (str_units == 'Chemiluminescence'):
                        df.loc[irow, col_units] = 'Chemiluminescence units'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process gsta3 expression units
    result_type = "gsta3 expression"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process expression levels units
    result_type = "expression levels"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process glutathione remaining units
    result_type = "glutathione remaining"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process effective concentration 5 percent units
    result_type = "effective concentration 5 percent"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process effective concentration 10 percent units
    result_type = "effective concentration 10 percent"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
        
    # Process effective concentration 20 percent units
    result_type = "effective concentration 20 percent"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process effective concentration 50 percent units
    result_type = "effective concentration 50 percent"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process total copper units
    result_type = "total copper"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process hydrogen peroxide production units
    result_type = "hydrogen peroxide production"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Relative Light Units' or 
                    str_units == 'Light Units'):
                    if (str_units == 'Light Units'):
                        df.loc[irow, col_units] = 'Relative Light Units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process recovery la4 cells units
    # Must double check unit conversion from 10000/well to 1000/well.
    result_type = "recovery la4 cells"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == '1000/well' or 
                    str_units == '10000/well'):
                    if (str_units == '10000/well'):
                        df.loc[irow, col_units] = '1000/well' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 10
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 10
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 10 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process endothelial nitric oxide synthase units
    result_type = "endothelial nitric oxide synthase"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process rna content units
    result_type = "rna content"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanograms/microliter' or 
                    str_units == 'micrograms/microliter'):
                    if (str_units == 'micrograms/microliter'):
                        df.loc[irow, col_units] = 'nanograms/microliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process genomics (microarrays), genes induced units
    # Must double check unit conversion from percent to fold change over control.
    result_type = "genomics (microarrays), genes induced"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'percent'):
                    if (str_units == 'percent'):
                        df.loc[irow, col_units] = 'fold change over control' 
                        if (df[col_value].iloc[irow] >= 0):
                            df.loc[irow, col_value] = 1 + (df[col_value].iloc[irow] / 100)
                        else:
                            df.loc[irow, col_value] = -1 + (df[col_value].iloc[irow] / 100)  
                        if (col_low in list_result_low):
                            if (df[col_low].iloc[irow] >= 0.0):
                                df[irow, col_low] = 1 + (df[col_low].iloc[irow] / 100)
                            else:
                                df[irow, col_low] = -1 + (df[col_low].iloc[irow] / 100)                            
                        if (col_high in list_result_high):
                            if (df[col_high].iloc[irow] >= 0.0):
                                df[irow, col_high] = 1 + (df[col_high].iloc[irow] / 100)
                            else:
                                df[irow, col_high] = -1 + (df[col_high].iloc[irow] / 100)                        
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process genomics (microarrays), genes repressed units
    result_type = "genomics (microarrays), genes repressed"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process percent control units
    result_type = "percent control"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'fraction'):
                    if (str_units == 'fraction'):
                        df.loc[irow, col_units] = 'percent' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+02
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+02
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+02 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    
    # Process ratio reactive oxygen species to reactive nitrogen species units
    result_type = "ratio reactive oxygen species to reactive nitrogen species"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'fold activity' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process 8-oxo-dg activity units
    result_type = "8-oxo-dg activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'fold activity' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process ap sites production units
    result_type = "ap sites production"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'fold activity' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process total endogenous dna units
    result_type = "total endogenous dna"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'fold activity' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process 4-hne activity units
    result_type = "4-hne activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'fold activity' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process mda activity units
    result_type = "mda activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold activity' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'fold activity' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process metabolic activity units
    result_type = "metabolic activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process il-6 activity units
    result_type = "il-6 activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'picograms/milliliter' or 
                    str_units == 'nanograms/milliliter'):
                    if (str_units == 'nanograms/milliliter'):
                        df.loc[irow, col_units] = 'picograms/milliliter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process free radical generation units
    result_type = "free radical generation"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'Relative Light Units'):
                    if (str_units == 'Relative light units'):
                        df.loc[irow, col_units] = 'Chemiluminescence units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process change in total spikes units
    result_type = "change in total spikes"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process firing rat units
    result_type = "firing rate"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process stain index units
    result_type = "stain index"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process cytokinesis-blocked proliferation index units
    # This result has units of ratio and percent.  Both units have values that are very
    # similar to one another. That being said, we are making the assumption that ratio and 
    # percent are interchangeable for this result type. 
    result_type = "cytokinesis-blocked proliferation index"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'percent'):
                    if (str_units == 'percent'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process number of silver particles units
    result_type = "number of silver particles"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'ratio' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process forward scatter units
    result_type = "forward scatter"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process concentration total zinc units
    result_type = "concentration total zinc"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'micrograms/liter' or 
                    str_units == 'milligrams/liter'):
                    if (str_units == 'milligrams/liter'):
                        df.loc[irow, col_units] = 'micrograms/liter' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+03
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+03
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+03 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process g1-phase cells units
    result_type = "g1-phase cells"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'percent' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process are activity units
    result_type = "are activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'Relative Light Units'):
                    if (str_units == 'Relative Light Units'):
                        df.loc[irow, col_units] = 'Chemiluminescence units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process endothelin-1 units
    result_type = "endothelin-1"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process surface area units
    result_type = "surface area"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'nanometers squared' or 
                    str_units == 'micrometers squared'):
                    if (str_units == 'micrometers squared'):
                        df.loc[irow, col_units] = 'nanometers squared' 
                        df.loc[irow, col_value] = df.loc[irow, col_value] * 1.0E+06
                        if (col_low in list_result_low):
                            df.loc[irow, col_low] = df.loc[irow, col_low] * 1.0E+06
                        if (col_high in list_result_high):
                            df.loc[irow, col_high] = df.loc[irow, col_high] * 1.0E+06 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process are/nrf2 activity units
    result_type = "are/nrf2 activity"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'Chemiluminescence units' or 
                    str_units == 'Relative Light Units'):
                    if (str_units == 'Relative Light Units'):
                        df.loc[irow, col_units] = 'Chemiluminescence units' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process mitosox fluorescence units
    result_type = "mitosox fluorescence"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'ratio to control' or 
                    str_units == 'ratio'):
                    if (str_units == 'ratio'):
                        df.loc[irow, col_units] = 'ratio to control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process s-phase cells units
    result_type = "s-phase cells"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'percent' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process endothelin receptor b  units
    result_type = "endothelin receptor b"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process g2/m-phase cells  units
    result_type = "g2/m-phase cells"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'percent' or 
                    str_units == 'unitless'):
                    if (str_units == 'unitless'):
                        df.loc[irow, col_units] = 'percent'
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process tissue plasminogen activator units
    result_type = "tissue plasminogen activator"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process plasminogen activator inhibitor-1 units
    result_type = "plasminogen activator inhibitor-1"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = "Unrecognized units: " + str_units + ", column = " + str(col_value) 
                    + " at row " + str(irow)
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)   
    
    # Process tissue factor units
    result_type = "tissue factor"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process tummor necrosis factor-α units
    result_type = "tummor necrosis factor-α"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process angiotensin receptor type 1a units
    result_type = "angiotensin receptor type 1a"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     
    
    # Process caveolin-1 units
    result_type = "caveolin-1"
    col_value = result_type + " result_value"
    col_units = result_type + " result_unit"
    col_low = result_type + " result_low"
    col_high = result_type + " result_high"
    df[col_value] = df[df[col_value]!= None][col_value].astype(float)
    if (col_low in list_result_low):
        df[col_low] = df[df[col_low]!= None][col_low].astype(float)
    if (col_high in list_result_high):
        df[col_high] = df[df[col_high]!= None][col_high].astype(float)
    try:
        for irow in range(0, nrow):
            if (math.isnan(df[col_value].iloc[irow])):
                continue
            else:
                str_units = df[col_units].iloc[irow]
                if (str_units == 'fold change over control' or 
                    str_units == 'fold change'):
                    if (str_units == 'fold change'):
                        df.loc[irow, col_units] = 'fold change over control' 
                else:
                    error_message = ("Unrecognized units: " + str_units + ", column = " + 
                                     str(col_value) + " at row " + str(irow))
                    raise ValueError(error_message) 
                                       
    except ValueError as msg:
        error_message = str(msg) + ", " + str(col_value) + ", " + col_units +  ", row = " + str(irow)
        print(error_message)     