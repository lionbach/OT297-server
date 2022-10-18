package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.MemberRequest;

import com.alkemy.ong.models.response.MemberPaginatedResponse;
import com.alkemy.ong.models.response.MemberResponse;
import com.alkemy.ong.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// swagger
@Api(tags = "Member", description = "Member Crud")
// swagger end
@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    // swagger
    @ApiOperation(value = "Create Member", notes = "Allows an user to insert a member")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request - cannot be null - cannot be empty - cannot be blank"),
            @ApiResponse(code = 500, message = "Internal server error - failed to try save image")
    })
    // swagger end
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<MemberResponse> save(@Valid @RequestBody MemberRequest memberRequest) {
        MemberResponse memberSavedResponse  = memberService.save(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberSavedResponse);
    }

    // swagger
    @ApiOperation(value = "Delete Member", notes = "Allows an admin to delete a member")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Not found - id not exist - The member to delete does not exist")
    })
    // swagger end
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // swagger
    @ApiOperation(value = "Get all Members", notes = "Allows an admin to get a list of all members")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    // swagger end
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<MemberResponse>> findAll(){
        List<MemberResponse> membersResponse  = memberService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(membersResponse);
    }

    // swagger
    @ApiOperation(value = "Get Members with pagination", notes = "Allows an user to get a list of 10 members and the previous and next pages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    // swagger end
    @GetMapping("/paginated")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MemberPaginatedResponse> findAllPaginated(@RequestParam(defaultValue = "1") Integer page){
        MemberPaginatedResponse response  = memberService.findAllPaginated(page, 10);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // swagger
    @ApiOperation(value = "Update Member", notes = "Allows an user to update data of a member")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request - cannot be null - cannot be empty - cannot be blank"),
            @ApiResponse(code = 404, message = "Not found - id not exist - The member to update does not exist"),
            @ApiResponse(code = 500, message = "Internal server error - failed to try save image")

    })
    // swagger end
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MemberResponse> update(@Valid @RequestBody MemberRequest memberRequest, @PathVariable Long id ) {
        MemberResponse memberResponse  = memberService.update(memberRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

}
