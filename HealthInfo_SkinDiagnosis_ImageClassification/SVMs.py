
from Functions import *
from sklearn.utils import shuffle

### Preparing Dataset:

##------------------------------------------------------------------------------------------------
images = create_dataset('/Users/yywxenia/PycharmProjects/Test_image/matrix.txt', 0, 16384, 16384)
n_samples = len(images['target'])
print "Number of samples: ", n_samples
X0 = images['data']
y = images['target']
X, y = shuffle(X0, y, random_state=0)

X_train, y_train, X_test, y_test = train_test_size(X, y, 0.3)
Tr_accuracy, accuracy, Time_train, Time_test = SVMs(0.5, X_train, y_train, X_test, y_test)

print "Training accuracy in 'linear', 'poly', 'rbf', 'sigmoid': ", Tr_accuracy
print "Testing accuracy in 'linear', 'poly', 'rbf', 'sigmoid': ", accuracy
