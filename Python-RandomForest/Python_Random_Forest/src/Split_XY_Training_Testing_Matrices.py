'''
Created on Mar 9, 2021

@author: Wmelende
'''
from sklearn.model_selection import train_test_split

def split_X_y_training_testing(X, y):
    # Split the data into training and testing sets.
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
    
    return X_train, X_test, y_train, y_test