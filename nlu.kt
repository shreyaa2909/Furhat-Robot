package furhatos.app.iisembodiment.nlu
import furhatos.nlu.ComplexEnumEntity
import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.ListEntity
import furhatos.nlu.common.Number
import furhatos.util.Language


//Gesture 1- open palm
class One : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language): List<String> {
        return listOf(  "yes","open palm","open")
    }
}
class GestureOne(val ones: OneList? = null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@ones")
    }
}
class OneList : ListEntity<OpenPalm>()
class OpenPalm(
        val one: One? = null) : ComplexEnumEntity () {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@one")
    }

}
//Gesture 2 - open dorsal
class Two : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language): List<String> {
        return listOf( "no", "open dorsal", "two")
    }
}
class GestureTwo(val twos: TwoList? = null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@twos")
    }
}
class TwoList : ListEntity<OpenDorsal>()
class OpenDorsal(
        val two: Two? = null) : ComplexEnumEntity () {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@two")
    }

}

//Gesture 3- fist palm
class Three : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language): List<String> {
        return listOf( "fistpalm", "three","lunch","chicken caesar salad", "sandwiches", "deluxe burger", "turkey burger", "greek salad")
    }
}
class GestureThree(val threes: ThreeList? = null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@threes","I want @threes",
                "I would like an english breakfast @threes",
                "I want to buy @threes")
    }
}
class ThreeList : ListEntity<FistPalm>()
class FistPalm(    // We need to update the BuyDrink intent to take in a DrinkList
        val countlunch : furhatos.nlu.common.Number? = Number(1),
        val three: Three? = null) : ComplexEnumEntity () {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@countlunch @three", "@three")
    }
    override fun toText(): String {
        return generate("$countlunch $three")
    }

}
//Gesture 4- fist dorsal
class Four : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language): List<String> {
        return listOf( "fist dorsal", "four", "breakfast","pancake","cerial", "fruit salad","waffel","eggs")
    }
}
class GestureFour(val fours: FourList? = null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@fours","I want @fours",
                "I would like an english breakfast @fours",
                "I want to buy @fours")
    }
}
class FourList : ListEntity<FistDorsal>()
class FistDorsal(    // We need to update the BuyDrink intent to take in a DrinkList
        val countbreakfast : furhatos.nlu.common.Number? = Number(1),
        val four: Four? = null) : ComplexEnumEntity () {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@countbreakfast @four", "@four")
    }
    override fun toText(): String {
        return generate("$countbreakfast $four")
    }
}
//Gesture 5- Three finger palm
class Five : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language): List<String> {
        return listOf(  "three finger palm","dinner","italian flat bread","classic bruschetta","chicken quesedillas","hawaiian pizza","margherita pizza")
    }
}
class GestureFive(val fives: FiveList? = null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@fives","I want @fives",
                "I would like an english breakfast @fives",
                "I want to buy @fives")
    }
}
class FiveList : ListEntity<ThreeFingerPalm>()
class ThreeFingerPalm(    // We need to update the BuyDrink intent to take in a DrinkList
        val countdinner : furhatos.nlu.common.Number? = Number(1),
        val five: Five? = null) : ComplexEnumEntity () {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@countdinner @five", "@five")
    }
    override fun toText(): String {
        return generate("$countdinner $five")
    }
}

//Gesture 6- three finger dorsal
class Six : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language): List<String> {
        return listOf( "three finger dorsal", "six","drink","beer","wine","water")
    }
}
class GestureSix(val sixs: SixList? = null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@sixs","I want @sixs",
                "I would like an english breakfast @sixs",
                "I want to buy @sixs")
    }
}
class SixList : ListEntity<ThreeFingerDorsal>()
class ThreeFingerDorsal(    // We need to update the BuyDrink intent to take in a DrinkList
        val countdrink : furhatos.nlu.common.Number? = Number(1),
        val six: Six? = null) : ComplexEnumEntity () {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@countdrink @six", "@six")
    }
    override fun toText(): String {
        return generate("$countdrink $six")
    }
}


/* Define RequestOptions Intent
What drinks we have in our coffee shop
 */
class RequestOptions: Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?",
                "What are the alternatives?")
    }
}

