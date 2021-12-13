'''
Created on Feb 17, 2021

@author: Wmelende
'''


def fix_categorical_data_errors(df):
    # Fix typos/misspellings
    df["CoatingComposition"].replace({"polyvinyl pyrrolidone": "polyvinylpyrrolidone"}, inplace = True)
    df["Shape"].replace({"sphere":"spherical", "shperical":"spherical"}, inplace = True)
    
    # Combine subpathway and sub pathway columns into subpathway column.
    df["subpathway parameter_nonnum"] = df["subpathway parameter_nonnum"].combine_first(df["sub pathway parameter_nonnum"])
    
    # Delete 'sub pathway parameter_nonnum' column
    df.drop("sub pathway parameter_nonnum", axis = 1, inplace = True)
    
    # Create list of categorical columns.
    categorical_columns = ['CoreComposition', 'ShellComposition', 'CoatingComposition', 
                           'Shape', 'SurfaceChargeType', 'particle concentration parameter_nonnum', 
                           'cell type parameter_nonnum', 'subject parameter_nonnum', 
                           'light parameter_nonnum']
    
    # Change case of categories to lowercase.
    for columns in categorical_columns:
        df[columns] = df[columns].str.lower()
    
                      