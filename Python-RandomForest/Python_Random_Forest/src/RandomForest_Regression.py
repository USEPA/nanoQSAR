'''
Created on Mar 9, 2021

@author: Wmelende
'''
import numpy as np
from sklearn.ensemble import RandomForestRegressor
from sklearn import metrics

def perform_RandomForest_regression(X_train, y_train, X_test, y_test):
    regressor = RandomForestRegressor(n_estimators = 20, random_state = 0)
    regressor.fit(X_train, y_train)
    y_pred = regressor.predict(X_test)
    
    
    # Evaluate the algorithm
    mean_abs_error = metrics.mean_absolute_error(y_test, y_pred)
    mean_sqr_error = metrics.mean_squared_error(y_test, y_pred)
    root_mean_sqr_error = np.sqrt(metrics.mean_squared_error(y_test, y_pred))
    
    print('Rsq for Training set = ',regressor.score(X_train, y_train))
    print('Rsq for Testing set: ',regressor.score(X_test, y_test))
    print('Mean absolute error: ', mean_abs_error)
    print('Mean square error: ', mean_sqr_error)
    print('Root mean square error: ', root_mean_sqr_error)
    