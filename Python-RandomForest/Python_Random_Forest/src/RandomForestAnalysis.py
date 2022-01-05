'''
Created on Mar 9, 2021

@author: Wmelende
'''
import numpy as np
from sklearn.ensemble import RandomForestRegressor
from sklearn import metrics
from numpy.core.numeric import indices

class RandomForestAnalysis():
    
    def __init__(self, train_features):
        self.train_features = train_features
            
    def train(self, X_train, y_train):
        self.X_train = X_train
        self.y_train = y_train
        self.regressor = RandomForestRegressor(n_estimators = 100, random_state = 0)
        self.regressor.fit(self.X_train, self.y_train)
        return self.regressor
    
    def predict(self, X_test):
        return self.regressor.predict(X_test)
    
    # Evaluate the algorithm
    def evaluate(self, y_test, X_test):
        y_pred = self.predict(X_test)
        mean_abs_error = metrics.mean_absolute_error(y_test, y_pred)
        mean_sqr_error = metrics.mean_squared_error(y_test, y_pred)
        root_mean_sqr_error = np.sqrt(metrics.mean_squared_error(y_test, y_pred))
    
        print('Rsq for Training set = ',self.regressor.score(self.X_train, self.y_train))
        print('Rsq for Testing set: ',self.regressor.score(X_test, y_test))
        print('Mean absolute error: ', mean_abs_error)
        print('Mean square error: ', mean_sqr_error)
        print('Root mean square error: ', root_mean_sqr_error)
    
        importances = self.regressor.feature_importances_
        indices = np.argsort(importances)[::-1]

    # Print the feature ranking
        print("\nFeature ranking:\n")

        for f in range(len(indices)):
            thisimp = importances[indices[f]]
            if thisimp > 2.0e-2:
                print("%d) %s \t= %f" % (f,self.train_features[indices[f]],importances[indices[f]]))


    