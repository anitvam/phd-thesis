val athletesWebPage = URL("https://olympics.com/en/athletes/")
val athleteNamePattern = Regex("""<span class="athlete--name no-trunc">(.+?)<\/span>""")

mas {
  athletesWebPage.readText() // reads the Web page as HTML text
    .let { athleteNamePattern.findAll(it) } // selects atheles' names spans
    .take(10) // takes the first 10 athletes
    .map { it.groupValues[1] } // selects atheles' names
    .map { it.replace(" ", "-") } // replaces spaces with dashes in names
    .forEach { 
      agent("athlete-$it") { // creates one agent for each athlete
        beliefs { /* ... */ }
        goals { /* ... */ }
        plans { /* ... */ }
      }
    }
}