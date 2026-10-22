// vars/trivyScan.groovy
def call(Map config = [:]) {
    def scanType = config.scanType   // 'fs' or 'image'
    def target = config.target       // path (e.g. '.') or image name
    def reportName = config.reportName ?: "trivy-${scanType}-report.html"

    if (scanType == 'fs') {
        sh "trivy fs --format table -o ${reportName} ${target}"
    } else if (scanType == 'image') {
        sh "trivy image --format table -o ${reportName} ${target}"
    } else {
        error "Unsupported Trivy scan type: ${scanType}. Use 'fs' or 'image'."
    }
}
