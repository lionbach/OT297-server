package com.alkemy.ong.controller;


import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.request.NewsUpdateRequest;
import com.alkemy.ong.models.response.*;
import com.alkemy.ong.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/news")
@Api(description ="News CRUD" , tags = "News")
public class NewsController {

    @Autowired
    NewsService newsService;

    @PostMapping
    @ApiOperation(value = "Create News", notes = "Allows Admin to insert news")
    @ApiResponses({@ApiResponse(code = 201, message = "News created!")})
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest newsRequest) throws IOException {
        NewsResponse saveResponse = newsService.save(newsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveResponse);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update News By ID", notes = "Allows Admin to update an existing news by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "News updated!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    public ResponseEntity<NewsResponse> updateNews(@Valid @PathVariable Long id, @RequestBody NewsUpdateRequest newsUpdateRequest, @RequestHeader(name = "Authorization") String token) throws IOException {
        //return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userUpdateRequest));
        return newsService.update(id, newsUpdateRequest, token);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get News By ID", notes = "Returns all details of news by ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested news"),
                   @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    public ResponseEntity<NewsResponse> getNews(@RequestHeader(name = "Authorization") @PathVariable Long id) throws IOException {
        return newsService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Soft Delete News By ID", notes = "Allows Admin to delete news by ID")
    @ApiResponses({@ApiResponse(code = 204, message = "News soft deleted!"),
                   @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token){
        return newsService.delete(id, token);
    }

    @GetMapping("/{id}/comments")
    @ApiOperation(value = "Get Comments By News ID", notes = "Returns all the comments according to the News ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested comments"),
                   @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    public ResponseEntity<List<CommentResponse>> getAllComments(@PathVariable Long id){
        return newsService.getAllCommentsById(id);
    }

    @GetMapping("/paginated")
    @ApiOperation(value = "Get  All News" , notes = "Returns All News ")
    @ApiResponses({@ApiResponse(code = 200, message = "Return All news created"),
                   @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<NewsPaginatedResponse> findAllPaginated(@RequestParam(defaultValue = "1") Integer page) throws IOException {
        NewsPaginatedResponse response  = newsService.findAllPaginated(page, 10);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
