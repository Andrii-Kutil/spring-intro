package intro.controller;

import intro.UserResponseDto;
import intro.model.User;
import intro.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/inject")
    public String injectUsers() {
        userService.add(new User("Andrii", "andrii@i.ua"));
        userService.add(new User("Dima", "dima@i.ua"));
        userService.add(new User("Oleg", "oleg@i.ua"));
        userService.add(new User("John", "john@i.ua"));
        return "My congratulations, you create four users!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.get(userId);
        return getUserResponseDto(user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<User> listUsers = userService.listUsers();
        return listUsers.stream()
                .map(this::getUserResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
