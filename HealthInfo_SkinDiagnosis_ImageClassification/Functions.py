from sklearn import svm
from sklearn.metrics import accuracy_score
from pylab import *
import time
import numpy as np
import urllib
from sklearn.decomposition import PCA
from sklearn import preprocessing
from sklearn.utils import shuffle

### Generate Datasets
###---------------------------------------------------------------------------------------
def create_dataset(url, i, j, m):
    raw_data = urllib.urlopen(url)
    dataset = np.loadtxt(raw_data, delimiter=",")
    print "dataset.shape:", (dataset.shape)
    # Separate the data from the target attributes
    data = dataset[:,i:j]
    target = dataset[:,m]
    target = target.astype(np.int32)

    myDict = {}
    myDict['data']= data
    myDict['target']= target

    return myDict

### Customize the size of training set with fixed testing set
###---------------------------------------------------------------------------------------
def train_test_size(X, y, percent): # percent(<=0.8) means the percentage of full data you want to use as training set.
    n_samples=len(y)
    i=int(n_samples*percent) #fixed size
    j=int(n_samples-int(n_samples*percent))
    assert(i <= j)
    X_test = X[:i]
    y_test = y[:i]
    X_train = X[i:]
    y_train = y[i:]

    print "Training data size:", len(y_train)
    print "Testing data size:", len(y_test)
    return X_train, y_train, X_test, y_test

### Normalized dataset
###---------------------------------------------------------------------------------------
def normalize_featDim(dataset):
    X_normalized = preprocessing.normalize(dataset, axis=0)
    return X_normalized


### SVMs:
###---------------------------------------------------------------------------------------
def SVMs(gamma_value, XTrain, yTrain, XTest, yTestCorrect):
    accuracy=[]
    Tr_accuracy=[]
    Time_train = []
    Time_test = []

    for kernels in ('linear', 'poly', 'rbf', 'sigmoid'):
        clf = svm.SVC(kernel=kernels, gamma=gamma_value)

        start = time.time()
        clf = clf.fit(XTrain, yTrain)
        end = time.time()
        time_elapse1 = (end - start)
        Time_train.append(time_elapse1)
        # print "-->time used on training model: ", time_elapse1

        Train_predicted = clf.predict(XTrain)
        Tr_accs = accuracy_score(yTrain, Train_predicted) #Training accuracy
        Tr_accuracy.append(Tr_accs)

        start2 = time.time()
        predicted = clf.predict(XTest)
        end2 = time.time()
        time_elapse2 = (end2 - start2)
        Time_test.append(time_elapse2)
        # print "---->time used on testing model: ", time_elapse2

        accs = accuracy_score(yTestCorrect, predicted) # Testing accuracy
        accuracy.append(accs)

    return Tr_accuracy, accuracy, Time_train, Time_test


### PCA Transformation
###---------------------------------------------------------------------------------------
def PCA_result(n_component, datasets):
    pca = PCA(n_components=n_component)
    pca_result = pca.fit_transform(datasets)
    print "PCA dataset.shape:", (pca_result.shape)
    return pca_result