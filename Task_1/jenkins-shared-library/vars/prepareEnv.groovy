
def call(Map config = [:]) {

    echo "Preparing environment and extracting SCM metadata..."

    // Actual checked-out commit
    def commitId = sh(
        script: 'git rev-parse HEAD',
        returnStdout: true
    ).trim()

    // Actual Git committer
    def committer = sh(
        script: "git log -1 --format='%cn'",
        returnStdout: true
    ).trim()

    def branchName = config.branch ?:
                     env.gitlabBranch ?:
                     env.GIT_BRANCH ?:
                     env.BRANCH_NAME ?:
                     'unknown'

    // GitLab user who triggered the push
    def triggeredBy = config.triggeredBy ?:
                      env.gitlabUserName ?:
                      'Unknown'

    // Export variables to pipeline environment
    env.COMMITTED_BY = committer
    env.COMMIT_HASH = commitId
    env.BUILD_BRANCH = branchName
    env.TRIGGERED_BY = triggeredBy

    echo "--- Environment Summary ---"
    echo "Committer   : ${committer}"
    echo "Triggered by: ${triggeredBy}"
    echo "Commit ID   : ${commitId}"
    echo "Branch      : ${branchName}"
    echo "---------------------------"

    return [
        committer: committer,
        triggeredBy: triggeredBy,
        commitId: commitId,
        branch: branchName
    ]
}
