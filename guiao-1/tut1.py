f = open('ciphertext1.txt', 'r')

LETTERS = 'ABCEFGHIJKLMNOPQRSTUVWXYZ'
LETTERFREQ = [8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.361, 0.15, 1.974, 0.074 ]
LETTERCOUNT = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
LETTERCOUNTFREQ = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
COUNT = 0
counte = 0

message1 = ''
for line in f:
	message1 = message1 + line
	for letter in line:
		if letter in LETTERS:
			COUNT += 1
			NUM = LETTERS.find(letter)			
			LETTERCOUNT[NUM] += 1

for num in LETTERCOUNT:
	LETTERCOUNTFREQ[counte] = float(num)/float(COUNT)*100
	counte += 1

print "LETTERCOUNT --->"
print LETTERCOUNT
print "LETTERCOUNTFREQ ---->"
print LETTERCOUNTFREQ
print "COUNT"
print COUNT
print "counte"
print counte
message = message1 


key = abs(LETTERCOUNTFREQ.index(max(LETTERCOUNTFREQ))-4)

print key
# tells the program to encrypt or decrypt
mode = 'decrypt' # set to 'encrypt' or 'decrypt'
# every possible symbol that can be encrypted

# stores the encrypted/decrypted form of the message
translated = ''
# capitalize the string in message
message = message.upper()
print message
# run the encryption/decryption code on each symbol in the message string
for symbol in message:
	if symbol in LETTERS:
	# get the encrypted (or decrypted) number for this symbol
		num = LETTERS.find(symbol) # get the number of the symbol
		if mode == 'encrypt':
			num = num + key
		elif mode == 'decrypt':
			num = num - key
		if num >= len(LETTERS):
			num = num - len(LETTERS)
		elif num < 0:
			num = num + len(LETTERS)
			# add encrypted/decrypted number's symbol at the end of translat
		translated = translated + LETTERS[num]
	else:
	# just add the symbol without encrypting/decrypting
		translated = translated + symbol
# print the encrypted/decrypted string to the screen
print(translated)

transptranslated = ''

for i in range (1,11):
	transptranslated = transptranslated + '\n'	
	for l in range(1,31):
		if len(translated) < (l*i+l):
			break
		transptranslated = transptranslated + translated[l*i+l-2]
print transptranslated

