import math
from unittest import result

stopNumber = 5
triangleSequence = 2
currentTriangleNumber = 1
stopFlag = True

def getNumberOfDivisors(currentTriangleNumber):
    divisorsCount = 0
    sqrtRange = int(math.sqrt(currentTriangleNumber))
    for i in range(sqrtRange):
        if i > 0:
            if currentTriangleNumber % i == 0:
                divisorsCount+=2
    if divisorsCount > stopNumber:
        return False
    return True


while(stopFlag):
    currentTriangleNumber += triangleSequence
    triangleSequence+=1
    stopFlag = getNumberOfDivisors(currentTriangleNumber)
    print(currentTriangleNumber)

print(currentTriangleNumber)

sequenceSize = 48
answer = 0
for i in range(sequenceSize):
    answer += i

print(answer)

