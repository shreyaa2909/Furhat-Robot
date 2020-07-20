package furhatos.app.iisembodiment

import furhatos.app.iisembodiment.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class IisembodimentSkill : Skill() {
    override fun start() {
        /* This method starts your skill*
        * Here we create a new Flow instance
        * and runs an initial state Idle*/
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
