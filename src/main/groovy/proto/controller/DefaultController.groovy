package proto.controller

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import proto.domain.UploadedFile
import proto.domain.UploadedFileRepository

import javax.transaction.Transactional

@Slf4j
@CompileStatic
@Transactional
@Controller
class DefaultController {
    @Autowired
    UploadedFileRepository repository

    @RequestMapping('/index')
    index() {
        log.info 'index called.'
        return 'index'
    }

    @PostMapping('/upload')
    upload(@RequestParam('files[]') MultipartFile file,
           @RequestParam('fileId') String fileId) {

        String name = file.getProperties().get('originalFilename')
        log.info "upload called: ${name}, fileId: ${fileId}"

        new File('files').mkdirs()
        def uploadedFile = new File("files/${fileId}")
        if( uploadedFile.isFile() ) uploadedFile.delete()
        uploadedFile << file.getBytes()

        repository.save(new UploadedFile(fileName: name, fileId: fileId, createdDate: new Date()))

        return 'index'
    }
}
