'''
Created on Mar 10, 2021

@author: Wmelende
'''

def impute_missing_data_of_numerical_columns(df):
    
    # Extract column names
    column_names = list(df.columns)
    
    # Make temporary copy of df.
    df_temp = df.copy()
    
    # Build list with columns to be imputed with zeros.
    columns_zero = ['OuterDiameterValue', 'SurfaceAreaValue', 'HydrodynamicDiameterValue', 
                    'particle concentration parameter_value','duration incubation parameter_value', 
                    'duration aging parameter_value', 'duration irradiation parameter_value', 
                    'concentration zinc parameter_value', 'concentration copper parameter_value', 
                    'irradiance parameter_value', 'irradiation power parameter_value'] 
    
    # Extract columns with additive_value
    subs_value = "additive_value"
    additive_columns  = [icol for icol in column_names if subs_value in icol]
    additive_columns.remove("fetal bovine serum additive_value")
    
    total_cols_zero = columns_zero + additive_columns
    
    # Build list with columns to be imputed with most common value.
    columns_most_common = ['Purity', 'ChargeAvg', 'duration exposure parameter_value',
                           'number of cells parameter_value', 'concentration carbon dioxide parameter_value',
                           'fetal bovine serum additive_value']
    
    columns_nonzero = ['temperature parameter_value']
    
    # Impute missing values with zeros.
    df_temp[total_cols_zero] = df_temp[total_cols_zero].fillna(value = 0.0)
    
    # Impute missing temperatures with 25.0 Celsius.
    df_temp[columns_nonzero] = df_temp[columns_nonzero].fillna(value = 25.0)
    
    # Use the most frequent/common value.  This is also known as the mode.
    df_temp = df_temp.fillna(df_temp[columns_most_common].mode().iloc[0])
    
    # Use the OuterDiameterValue
    df_temp['OuterDiameterLow'] = df_temp['OuterDiameterLow'].fillna(df_temp['OuterDiameterValue'])
    df_temp['OuterDiameterHigh'] = df_temp['OuterDiameterHigh'].fillna(df_temp['OuterDiameterValue'])
     
    return df_temp