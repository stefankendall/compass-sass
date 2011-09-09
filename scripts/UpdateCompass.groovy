includeTargets << grailsScript("Init")
includeTargets << new File("${compassScssIntegrationPluginDir}/scripts/_GetCompassInvoker.groovy")

def updateGem(String gem) {
    println "Updating gem: $gem"
    Process p = "jruby -S gem update $gem".execute()
    p.consumeProcessOutput(System.out, System.err)
    p.waitFor()
}

target(updateCompass: 'Install blueprint stylesheets') {
    println "Updating Compass plugins"
    def listOfGemsToUpgrade = ['compass', 'rb-fsevent']
    listOfGemsToUpgrade.each {
        updateGem(it)
    }
}

setDefaultTarget(updateCompass)