package furhatos.app.iisembodiment.flow
import furhatos.app.iisembodiment.lunch
import furhatos.app.iisembodiment.breakfast
import furhatos.app.iisembodiment.dinner
import furhatos.app.iisembodiment.drink

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.iisembodiment.nlu.*
import furhatos.gestures.Gestures

/* This is an interactive state.
* The Introduction State (general.kt) is passed
* as parameter to the state definition.
* The Start state inherits Greetings state*/
val Start : State = state(Greetings) {

    onEntry {
        furhat.ask("Hi there" + "Welcome to Shreya’s Kitchen" + "Do you like robots?")
    }

    onResponse<GestureOne>{
        furhat.say("That's Great")
        furhat.gesture(Gestures.Smile(duration=2.0, strength = 1.0))
        furhat.say("My name is Sai ")
        furhat.say("I will take your order today")
        goto(TakeOrder)
    }

    onResponse<GestureTwo>{
        furhat.say("I hope you don't mind me taking the order")
        furhat.gesture(Gestures.ExpressSad(duration=3.0, strength = 0.5))
        furhat.say("My name is Sai ")
        furhat.say("I will take your order today")
        goto(TakeOrder)
    }
}
val TakeOrder = state(Greetings){
    onEntry {
        random(
                { furhat.ask("What would you like to have") },
                { furhat.ask("Do you want something to eat") }
        )
    }

    /*
    We need to modify BuyDrink to save ordered drinks
    onResponse<BuyDrink> {
        furhat.say("${it.intent.drinks}, good choice!")
    }
*/
    onResponse<GestureThree> {
        val threes = it.intent.threes
        // Follow-up orders
        if (threes != null){
            goto(lunchOrderReceived(threes))
        }
        else{
            propagate()
        }
    }

    onResponse<GestureFour> {
        val fours = it.intent.fours
        // Follow-up orders
        if (fours != null){
            goto(breakfastOrderReceived(fours))
        }
        else{
            propagate()
        }
    }

    onResponse<GestureFive> {
        val fives = it.intent.fives
        // Follow-up orders
        if (fives != null){
            goto(dinnerOrderReceived(fives))
        }
        else{
            propagate()
        }
    }

    onResponse<GestureSix> {
        val sixs = it.intent.sixs
        // Follow-up orders
        if (sixs != null){
            goto(drinkOrderReceived(sixs))
        }
        else{
            propagate()
        }
    }


    onResponse<No>{
        furhat.ask("How can I help you then?")
    }

    onResponse<RequestOptions> {
        furhat.say("We serve all the major meals such as breakfast, lunch, and dinner " )
        furhat.say("We also serve alcoholic beverages such as beer and wine " )
        furhat.ask("What would you like to have?")
    }
}

fun lunchOrderReceived(threes: ThreeList) : State = state{
    onEntry {
        furhat.say("${threes.text}, what a lovely choice!")
        threes.list.forEach {
            users.current.lunch.threes.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Can you repeat please?")
    }

    onResponse<GestureThree> {
        val threes = it.intent.threes
        // Follow-up orders
        if (threes != null){
            goto(lunchOrderReceived(threes))
        }
        else{
            propagate()
        }
    }

    onResponse<No> {
        furhat.say("Okay, here is your order for ${users.current.lunch.threes}")
    }



}

fun breakfastOrderReceived(fours: FourList) : State = state{
    onEntry {
        furhat.say("${fours.text}, what a lovely choice!")
        fours.list.forEach {
            users.current.breakfast.fours.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Can you repeat please?")
    }

    onResponse<GestureFour> {
        val fours = it.intent.fours
        // Follow-up orders
        if (fours != null){
            goto(breakfastOrderReceived(fours))
        }
        else{
            propagate()
        }
    }

    onResponse<No> {
        furhat.say("Okay, here is your order for ${users.current.breakfast.fours}")
    }


}

fun dinnerOrderReceived(fives: FiveList) : State = state{
    onEntry {
        furhat.say("${fives.text}, what a lovely choice!")
        fives.list.forEach {
            users.current.dinner.fives.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Can you repeat please?")
    }

    onResponse<GestureFive> {
        val fives = it.intent.fives
        // Follow-up orders
        if (fives != null){
            goto(dinnerOrderReceived(fives))
        }
        else{
            propagate()
        }
    }

    onResponse<No> {
        furhat.say("Okay, here is your order for ${users.current.dinner.fives}")
    }


}

fun drinkOrderReceived(sixs: SixList) : State = state{
    onEntry {
        furhat.say("${sixs.text}, what a lovely choice!")
        sixs.list.forEach {
            users.current.drink.sixs.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Can you repeat please?")
    }

    onResponse<GestureSix> {
        val sixs = it.intent.sixs
        // Follow-up orders
        if (sixs != null){
            goto(drinkOrderReceived(sixs))
        }
        else{
            propagate()
        }
    }

    onResponse<No> {
        furhat.say("Okay, here is your order for ${users.current.drink.sixs}")
    }


}