import numpy as np
import urllib
from sklearn.decomposition import PCA
from sklearn import preprocessing

### Define datasets X_data and y_label
###---------------------------------------------------------------------------------------
def create_dataset(url, i, j, m):
    raw_data = urllib.urlopen(url)
    dataset = np.loadtxt(raw_data, delimiter=",")
    print "dataset.shape:", (dataset.shape)
    # Separate the data from the target attributes
    data = dataset[:,i:j]
    target = dataset[:,m]
    target = target.astype(np.int32)

    # *Create dictionary for iris data for plotting
    myDict = {}
    myDict['data']= data
    myDict['target']= target

    return myDict


### Customize training set with fixed testing set
###---------------------------------------------------------------------------------------
def train_test_size(X, y, percent): # percent(<=0.8) means the percentage of full data you want to use as training set.
    n_samples=len(y)
    i=int(n_samples*percent) #fixed size
    j=int(n_samples-int(n_samples*percent))
    # assert(i <= j)
    X_test = X[:i]
    y_test = y[:i]
    X_train = X[j:]
    y_train = y[j:]

    print "Training data size:", len(y_train)
    print "Testing data size:", len(y_test)
    return X_train, y_train, X_test, y_test


### Normalized dataset
###---------------------------------------------------------------------------------------
def normalize_featDim(dataset):
    X_normalized = preprocessing.normalize(dataset, axis=0)
    return X_normalized


### PCA Transformation
###---------------------------------------------------------------------------------------
def PCA_result(n_component, datasets):
    pca = PCA(n_components=n_component)
    pca_result = pca.fit_transform(datasets)
    print "PCA dataset.shape:", (pca_result.shape)
    return pca_result
