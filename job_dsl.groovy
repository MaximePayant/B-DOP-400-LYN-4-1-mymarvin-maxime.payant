folder('/Tools') {
    description("Folder for miscellaneous tools.")
}

job('/Tools/clone-repository') {
    parameters {
        stringParam("GIT_REPOSITORY_URL", "", "Git URL of the repository to clone")
    }
    steps {
      shell('git clone $GIT_REPOSITORY_URL')
    }
    wrappers {
        preBuildCleanup {
            deleteDirectories()
        }
    }
}

job('/Tools/SEED') {
    parameters {
        stringParam("GITHUB_NAME", "", 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam("DISPLAY_NAME", "", "Display name for the job")
    }

}

steps {
        sdl {
            text {
                job('$DISPLAY_NAME') {
                    scm {
                        git('$GITHUB_NAME')
                    }
                    trigger {
                        scm('@minutes')
                    }
                    wrappers {
                        preBuildCleanup {
                            deleteDirectories()
                        }
                    }
                    steps {
                        shell("make fclean")
                        shell("make")
                        shell("make tests_run")
                        shell("make clean")
                    }
                }
            }
        }
    }