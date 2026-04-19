package nl.inholland.game_library.contollers;

import nl.inholland.game_library.models.Game;
import nl.inholland.game_library.reposetories.GameRepository;
import nl.inholland.game_library.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping({"","/"})
    public String showProductList(Model model) {
        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);
        return "games/index";
    }

    @GetMapping("/show/{id}")
    public String showProduct(Model model, @PathVariable int id) {
        Game game = gameService.findById(id);
        model.addAttribute("game", game);
        return "games/show";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/create";
    }

    @PostMapping("/create")
    public String createGame(Game game) {
        gameService.save(game);
        return "redirect:/games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable int id) {
        Game game = gameService.findById(id);
        gameService.delete(game);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable int id) {
        Game game = gameService.findById(id);
        model.addAttribute("game", game);
        return "games/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateGame(@PathVariable int id, Game game) {
        game.setId(id);
        gameService.update(game);
        return "redirect:/games";
    }


}
