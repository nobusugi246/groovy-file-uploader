package proto.domain

import groovy.transform.Canonical
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

import javax.persistence.*

@Canonical
@Entity
class UploadedFile implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id

    String fileName

    String fileId

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    java.util.Date createdDate
}


@RepositoryRestResource
interface UploadedFileRepository extends PagingAndSortingRepository<UploadedFile, Long> {
    List<UploadedFile> findByFileName(@Param("name") String name);
}
