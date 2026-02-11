package BlueAndPurple.invoicing.controller;

import BlueAndPurple.invoicing.bucket.BucketFile;
import BlueAndPurple.invoicing.bucket.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        try(InputStream is = file.getInputStream()){
            MediaType type = MediaType.parseMediaType(file.getContentType());
            var bucketFile = new BucketFile(file.getOriginalFilename(), is, type, file.getSize());
            bucketService.upload(bucketFile);

            return ResponseEntity.ok("File send with sucess!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error send the file: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<String> getUrl(@RequestParam String filename){
        try{
            String url = bucketService.getUrl(filename);
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(url);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error to get the file URL: " + e.getMessage());
        }
    }
}
