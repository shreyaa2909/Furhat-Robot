package furhatos.app.iisembodiment

import furhatos.app.iisembodiment.nlu.OneList
import furhatos.app.iisembodiment.nlu.TwoList
import furhatos.app.iisembodiment.nlu.ThreeList
import furhatos.app.iisembodiment.nlu.FourList
import furhatos.app.iisembodiment.nlu.FiveList
import furhatos.app.iisembodiment.nlu.SixList

import furhatos.records.User  //Import User records

/* Storing a user's order on the user object*/

/* We define a Kotlin data class OrderData with a variable*/
class OneData (
        var ones : OneList = OneList()
)
class TwoData (
        var twos : TwoList = TwoList()
)
class ThreeData (
        var threes : ThreeList = ThreeList()
)
class FourData (
        var fours : FourList = FourList()
)
class FiveData (
        var fives : FiveList = FiveList()
)
class SixData (
        var sixs : SixList = SixList()
)
/* We add an extension variable 'order' to the User model of
* type OrderData*/
val User.lunch : ThreeData
    get() = data.getOrPut(ThreeData::class.qualifiedName, ThreeData())
val User.breakfast : FourData
    get() = data.getOrPut(FourData::class.qualifiedName, FourData())
val User.dinner : FiveData
    get() = data.getOrPut(FiveData::class.qualifiedName, FiveData())
val User.drink : SixData
    get() = data.getOrPut(SixData::class.qualifiedName, SixData())