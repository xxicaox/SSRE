import binascii
class rc4EncDec(object):
    def __init__(self, key=16):
        self.key = bin(key)
    def keygen(self):
	S = []
	K = []
        for value in xrange(256):
		S.append(value)
	for value in xrange(256):
		K.append(value % 8)
	for value in xrange(256):
		j=0
		j = (j + S[value])
	print K	
    def encrypt(self, amount):
        self.balance -= amount
    def decrypt(self):
        return self.balance < 0
rc4 = rc4EncDec()
rc4.keygen()
print rc4.key
print binascii.b2a_uu("16sa")
