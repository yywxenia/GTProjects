from PIL import Image
import numpy as np
from os import listdir
from os.path import isfile, join


def read_image_to_matrix(mypath):
  # mypath='/Users/yywxenia/Desktop/images/cp/'
  onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath,f))]

  for i in range(1,len(onlyfiles)):
    url = mypath+onlyfiles[i]
    # print "Pictures:", url
    im = Image.open(url).convert("L")
    im2 =im.resize((128,128))
    # im2.show()
    IM = np.array(im2)
    IM = IM.reshape((1, 16384)) #128*128=16384


    # Provide label for different images:
    if 'measles' in onlyfiles[i]:
      labels=1
    if 'herpes' in onlyfiles[i]:
      labels=2
    if 'ringworm' in onlyfiles[i]:
      labels=3
    if 'varicella' in onlyfiles[i]:
      labels=4


    IM = np.hstack((IM, np.array(labels).reshape((1,1))))
    if i==1:
      Final_Matrix = IM
    else:
      Final_Matrix = np.vstack((Final_Matrix, IM))

  print 'Shape of the Matrix:', Final_Matrix.shape

  txt_file = np.savetxt('matrix.txt', Final_Matrix, delimiter=',', fmt='%d')

  return txt_file

read_image_to_matrix('/Users/yywxenia/Desktop/images/cp/')
