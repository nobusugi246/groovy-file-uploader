package proto.controller

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Slf4j
@CompileStatic
@Controller
class DefaultController {
    @RequestMapping('/')
    index() {
        log.info 'index called.'
        return 'index'
    }

    @PostMapping('/upload')
    upload(@RequestParam('files[]') MultipartFile file,
           @RequestParam('fileId') String fileId) {
        log.info "upload called: ${file.getProperties().get('originalFilename')}, fileId: ${fileId}"

        new File('files').mkdirs()
        def uploadedFile = new File("files/${fileId}")
        if( uploadedFile.isFile() ) uploadedFile.delete()
        uploadedFile << file.getBytes()

        return 'index'
    }
}
