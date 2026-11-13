
// vars/prepareEnv.groovy

def call(Map config = [:]) {

    echo "Preparing environment and extracting SCM metadata..."

    // Get actual checked-out commit
    def commitId = sh(
        script: 'git rev-parse HEAD',
        returnStdout: true
    ).trim()

    // Get branch information
    def branchName = config.branch ?:
                     env.BRANCH_NAME ?:
                     env.GIT_BRANCH ?:
                     "unknown"

    // Get committer from Git metadata
    def committer = sh(
        script: "git log -1 --format='%cn'",
        returnStdout: true
    ).trim()

    def committerEmail = sh(
        script: "git log -1 --format='%ce'",
        returnStdout: true
    ).trim()

    // Export variables to pipeline environment
    env.COMMITTED_BY = committer
    env.COMMIT_HASH = commitId
    env.BUILD_BRANCH = branchName

    echo "--- Environment Summary ---"
    echo "Committer : ${committer}"
    echo "Email     : ${committerEmail}"
    echo "Commit ID : ${commitId}"
    echo "Branch    : ${branchName}"
    echo "---------------------------"

    return [
        committer: committer,
        email: committerEmail,
        commitId: commitId,
        branch: branchName
    ]
}
