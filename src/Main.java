import repository.FuncionarioRepository;
import service.FuncionarioService;

void main() {
    FuncionarioService service = new FuncionarioService(new FuncionarioRepository(), Clock.system(ZoneId.of("America/Sao_Paulo")));
    
}