//: Playground - noun: a place where people can play

import UIKit

print("Hello, Swift!")

var myVariable = 2000
myVariable = 2015
let myConstant = 2015

let implicitInteger = 70
let implicitDouble = 70.0
let explicitDouble: Double = 70

let label = "The width is "
let width = 94
let widthLabel = label + String(width)

let apples = 3
let oranges = 5
let appleSummary = "I have \(apples) apples."
let fruitSummary = "I have \(apples + oranges) pieces of fruit."

var shoppingList = ["catfish", "water", "tulips", "blue paint"]
shoppingList[1] = "bottle of water"

var occupations = ["Malcolm": "Captain",
                    "Kaylee": "Mechanic",]

occupations["Jayne"] = "Public Relations"

let emptyArray = [String]()
let emptyDictionary = [String: Float]()

shoppingList = []
occupations = [:]

let individualScores = [75, 43, 103, 87, 12]
var teamScore = 0
for socre in individualScores {
    if socre > 50{
        teamScore += 3
    }else{
        teamScore += 1
    }
    
}
print(teamScore)

var optionalString: String? = "hello"
print(optionalString == nil)

var optionalName: String? = "Tom"
var greeting = "Hello!"
if let name = optionalName{
    greeting = "Hello, \(name)"
}

let nickName: String? = "Tom"
let fullName: String = "John Appleseed"
let informalGreeting = "Hi \(nickName ?? fullName)"

let vegetable = "red pepper"
switch vegetable{
    case "celery":
        print("Add some raisins and make ants on a log.")
    case "cucumber", "watercress":
        print("That would make a good tea sandwich.")
    case let x where x.hasSuffix("pepper"):
        print("Is it a spicy \(x)?")
default:
    print("Everything tasts good in soup.")
}

let interestingNumbers = [
    "Prime": [2, 3, 5, 7, 11, 13],
    "Fibonacci": [1, 1, 2, 3, 5, 8],
    "Square": [1, 4, 9, 16, 25],
]

var largest = 0;
for (kind, numbers) in interestingNumbers{
    for number in numbers{
        if number > largest{
            largest = number
        }
    }
}
print(largest)

var n = 2
while n < 100{
    n = n * 2
}
print(n)

var m = 2
repeat{
    m = m * 2
}while m < 100
print(m)

var firstForLoop = 0
for i in 0..<4{
    firstForLoop += i
}
print(firstForLoop)

var sencedForLoop = 0
for i in 0...4{
    sencedForLoop += i
}
print(sencedForLoop)

var thirdForLoop = 0
for var i = 0; i < 4; ++i{
    thirdForLoop += i
}
print(thirdForLoop)

func greet(name: String, day: String) -> String {
    return "Hello \(name), today is \(day)."
}
greet("Bob", day:"Tue")

func calculateStatistics(scores: [Int]) -> (min: Int, max: Int,sum: Int){
    var min = scores[0]
    var max = scores[0];
    var sum = 0;
    
    for score in scores{
        if score > max{
            max = score
        }else if score < min{
            min = score
        }
        sum += score
    }
    
    return (min, max, sum)
}
let statistics = calculateStatistics([5, 3, 100, 3, 9])
print(statistics.min)

print(statistics.sum)
print(statistics.2)

func sumOf(numbers: Int...) -> Int{
    var sum = 0
    for number in numbers{
        sum += number
    }
    return sum
}

sumOf()
sumOf(42, 597, 12)

func returnFifteen() -> Int{
    var y = 10
    func add(){
        y += 5
    }
    add()
    return y
}

returnFifteen()

func makeIncrementer() -> ((Int) -> Int){
    func addOne(number: Int) -> Int{
        return 1 + number
    }
    return addOne
}

var increment = makeIncrementer()
increment(7)

func hasAnyMatches(list: [Int], condition: (Int) -> Bool) -> Bool{
    for item in list{
        if condition(item){
            return true
        }
    }
    return false
}

func lessThanTen(number: Int) -> Bool{
    return number < 10
}

var numbers = [20, 19, 7, 12]
hasAnyMatches(numbers, condition: lessThanTen)

numbers.map({
    (number: Int) -> Int in
    let result = 3 * number
    return result
})

let mappedNumbers = numbers.map({ number in 3 * number })
print(mappedNumbers)

let sortedNumbers = numbers.sort{ $0 > $1 }
print(sortedNumbers)

class Shape {
    var numberOfSides = 0
    func simpleDesciption() -> String{
        return "A shape with \(numberOfSides) sides."
    }
}

var shape = Shape()
shape.numberOfSides = 7
var shapeDescription = shape.simpleDesciption()

class NamedShape {
    var numberOfSides: Int = 0
    var name: String
    
    init(name: String){
        self.name = name
    }
    
    func simpleDesciption() -> String{
        return "A shape with \(numberOfSides) sides."
    }
}

class Square: NamedShape{
    var sideLength: Double
    
    init(sideLength: Double, name: String){
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 4
    }
    
    func area() -> Double {
        return sideLength * sideLength
    }
    
    override func simpleDesciption() -> String {
        return "A square with sides of length \(sideLength)."
    }
}

let test = Square(sideLength: 5.2, name: "my test square")
test.area()
test.simpleDesciption()


class EquilateralTriangle: NamedShape{
    var sideLength: Double = 0.0
    
    init(sideLength: Double, name: String){
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 3
    }
    
    var perimeter: Double{
        get{
            return 3.0 * sideLength
        }
        set{
            sideLength = newValue / 3.0
        }
    }
    
    override func simpleDesciption() -> String {
        return "An equilateral triangle with sides of length \(sideLength)."
    }
}

var triangle = EquilateralTriangle(sideLength: 3.1, name: "a triangle")
print(triangle.perimeter)
triangle.perimeter = 9.9
print(triangle.sideLength)

class TriangleAndSquare{
    var triangle: EquilateralTriangle{
        willSet{
            square.sideLength = newValue.sideLength
        }
    }
    var square: Square{
        willSet{
            triangle.sideLength = newValue.sideLength
        }
    }
    init(size: Double, name: String){
        square = Square(sideLength: size, name: name)
        triangle = EquilateralTriangle(sideLength: size, name: name)
    }
    
}
var triangleAndSquare = TriangleAndSquare(size: 10, name: "another test shape")
print(triangleAndSquare.square.sideLength)
print(triangleAndSquare.triangle.sideLength)
triangleAndSquare.square = Square(sideLength: 50, name: "larger square")
print(triangleAndSquare.triangle.sideLength)

let optionalSquare: Square? = Square(sideLength: 2.5, name: "optional square")
let sideLength = optionalSquare?.sideLength

enum Rank: Int{
    case Ace = 1
    case Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten
    case Jack, Queen, King
    func simpleDescription() -> String{
        switch self{
        case .Ace:
            return "ace"
        case .Jack:
            return "jack"
        case .Queen:
            return "queen"
        case .King:
            return "king"
        default:
            return String(self.rawValue)
        }
    }
}
let ace = Rank.Ace
let aceRawValue = ace.rawValue

if let convertedRank = Rank(rawValue: 3){
    let threeDescription = convertedRank.simpleDescription()
}

enum Suit{
    case Spades, Hearts, Diamonds, Clubs
    func simpleDesciption() -> String{
        switch self{
        case .Spades:
            return "spades"
        case .Hearts:
            return "hearts"
        case .Diamonds:
            return "diamonds"
        case .Clubs:
            return "cluds"
            
        }
    }
}
let hearts = Suit.Hearts
let heartsDescription = hearts.simpleDesciption()

struct Card{
    var rank: Rank
    var suit: Suit
    func simpleDescription() -> String{
        return "The \(rank.simpleDescription()) of \(suit.simpleDesciption())"
    }
}

let threeOfSpades = Card(rank: .Three, suit: .Spades)
let threeOfSpadesDescription = threeOfSpades.simpleDescription()

enum ServerResponse {
    case Result(String, String)
    case Error(String)
}

let success = ServerResponse.Result("6:00 am", "8:09 pm")
let failure = ServerResponse.Error("Out of cheese.")

switch success{
case let .Result(sunrise, sunset):
    print("Sunrise is at \(sunrise) and sunset is at \(sunset).")
case let .Error(error):
    print("Failure... \(error)")
}

protocol ExampleProtocol{
    var simpleDescription: String{ get }
    mutating func adjust()
}

class SimpleClass: ExampleProtocol {
    var simpleDescription: String = "A very simple class."
    var anotherProperty: Int = 69105
    func adjust() {
        simpleDescription += " Now 100% adjusted."
    }
}

var a = SimpleClass()
a.adjust()
let aDescription = a.simpleDescription

struct SimpleStructure: ExampleProtocol {
    var simpleDescription: String = "A simple structure"
    mutating func adjust() {
        simpleDescription += " (adjusted)"
    }
}
var b = SimpleStructure()
b.adjust()
let bDescription = b.simpleDescription


extension Int: ExampleProtocol{
    var simpleDescription: String{
        return "The number \(self)"
    }
    mutating func adjust() {
        self += 42
    }
}

print(7.simpleDescription)

let protocolValue: ExampleProtocol = a
print(protocolValue.simpleDescription)

func repeatItem<Item>(item: Item, numberOfTimes: Int) -> [Item]{
    var result = [Item]()
    for _ in 0..<numberOfTimes {
        result.append(item)
    }
    return result
}
repeatItem("knock", numberOfTimes:4)

enum OptionalValue<Wrapped>{
    case None
    case Some(Wrapped)
}
var possibleInteger: OptionalValue<Int> = .None
possibleInteger = .Some(100)

func anyCommonElements <T: SequenceType, U: SequenceType
    where T.Generator.Element: Equatable, T.Generator.Element == U.Generator.Element>(lhs: T, _ rhs: U) -> Bool{
        for lhsItem in lhs{
            for rhsItem in rhs{
                if lhsItem == rhsItem{
                    return true
                }
            }
        }
        return false
}

anyCommonElements([1, 2, 3], [4])















