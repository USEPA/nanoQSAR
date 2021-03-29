'''
Created on Mar 9, 2021

@author: Wmelende
'''

import random

# from sklearn.model_selection import train_test_split
import pandas as pd

def split_X_y_training_testing(dfX, dfy):
    # Split the data into training and testing sets.
    # X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
    
    # Combine dfX and dfY into a single DataFrame
    dfXy = pd.concat([dfX, dfy], axis = 1)
    
    # Extract column names
    column_names = list(dfXy.columns)
    
    # Determine number of rows
    nrows = len(dfXy.index)
    
    # Calculate 20% of rows
    nrows_testing = int(0.20 * nrows)
    
    # Create list of core compositions
    subs_value = "CoreComposition"
    core_comp_columns  = [icol for icol in column_names if subs_value in icol]
    
    dfXy[core_comp_columns] = dfXy[core_comp_columns].round(0).astype(int)
    
    # Remove Titanium dioxide from list
    core_comp_columns.remove('CoreComposition_titanium dioxide')
    
    # Create list of columns to be included in testing set.
    core_comp_test = []
    list_test_rows = []
    # Create list of columns to be included in traning set.
    core_comp_train = ['CoreComposition_titanium dioxide']
    
    # Calculate length of list
    num_core_comps = len(core_comp_columns)
    
    isum = 0
    for i in range(0, num_core_comps):
        print("i = ", i)
        # Randomly select core composition from list
        core_composition = random.choice(core_comp_columns)
        print("core composition = ", core_composition)
        # Determine number of times this core composition occurs (equal to 1).
        df1 = dfXy[core_composition].value_counts()
        print(df1)
        num1 = df1.values[df1.index == 1][0]
        print("num1 = ", num1)
        
        if (num1 < nrows_testing):
            isum = isum + num1
            print("isum = ", isum)
            if (isum <= nrows_testing):
                # Remove chosen core composition from list
                core_comp_columns.remove(core_composition)
                print("core_comp_columns = ", core_comp_columns)
                # Add core composition to test list
                core_comp_test.append(core_composition)
                print("core_comp_test = ",core_comp_test)
                dfTemp1 = dfXy[dfXy[core_composition] == 1]
                list_test_rows = list_test_rows + list(dfTemp1.index)
                # dfTemp0 = dfXy[dfXy[core_composition] == 0]
                if (i == 0):
                    dfXy_test = dfTemp1
                    # dfXy_train = dfTemp0
                else:                    
                    dfXy_test = pd.concat([dfXy_test, dfTemp1], axis = 0)
                    # dfXy_train = pd.concat([dfXy_train, dfTemp0], axis = 0)
                if (isum == nrows_testing):
                    break
            elif (isum > nrows_testing):
                diff1 = isum - nrows_testing
                diff2 = nrows_testing - (isum - num1)
                print("diff1 = ", diff1)
                print("diff2 = ", diff2)
                if (diff1 < diff2):
                    # Remove chosen core composition from list
                    core_comp_columns.remove(core_composition)
                    
                    # Add core composition to test list                    
                    core_comp_test.append(core_composition)
                    
                    dfTemp1 = dfXy[dfXy[core_composition] == 1]
                    list_test_rows = list_test_rows + list(dfTemp1.index)
                    # dfTemp0 = dfXy[dfXy[core_composition] == 0]
                    dfXy_test = pd.concat([dfXy_test, dfTemp1], axis = 0)
                    # dfXy_train = pd.concat([dfXy_train, dfTemp0], axis = 0) 
                break                          
        elif (num1 > nrows_testing and isum < nrows_testing):
            # Remove chosen core composition from list
            core_comp_columns.remove(core_composition)
            continue
        
        # Determine core compositions to be kept in training set
        core_comp_train = core_comp_train + core_comp_columns
        
        # Remove rows that correspond to the testing set.
        dfXy_train = dfXy.drop(list_test_rows, inplace = False, axis = 0)
        
        # Remove testing core compositions from training DataFrame
        # dfXy_train.drop(core_comp_test, inplace = True, axis = 1)
        
        # Remove training core compositions from testing DataFrame
        # dfXy_test.drop(core_comp_train, inplace = True, axis = 1)
        
        # Split X and Y matrices
        # Extract viability result column only (Y matrix)
        viability_column = "viability result_value"
        dfy_test = dfXy_test[[viability_column]]
        dfy_train = dfXy_train[[viability_column]]
        dfX_test = dfXy_test.drop(viability_column, inplace = False, axis = 1)
        dfX_train = dfXy_train.drop(viability_column, inplace = False, axis = 1)
    
    print(len(dfy_test))
    print(len(dfy_train))
    print(len(dfX_test))
    print(len(dfX_train))
    return dfX_train, dfX_test, dfy_train, dfy_test