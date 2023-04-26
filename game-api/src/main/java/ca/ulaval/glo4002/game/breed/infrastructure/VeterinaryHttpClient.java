package ca.ulaval.glo4002.game.breed.infrastructure;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import ca.ulaval.glo4002.game.breed.entities.Veterinary;
import ca.ulaval.glo4002.game.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurGender;
import ca.ulaval.glo4002.game.dinosaur.entities.enums.DinosaurSpecies;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;

public class VeterinaryHttpClient implements Veterinary {
    private static final String VETERINARY_URL = "http://localhost:8080/";
    private final DinosaurFactory dinosaurFactory;

    @Inject
    public VeterinaryHttpClient(DinosaurFactory dinosaurFactory) {
        this.dinosaurFactory = dinosaurFactory;
    }

    @Override
    public Optional<Dinosaur> breed(String babyName, Dinosaur fatherDinosaur, Dinosaur motherDinosaur) {
        Invocation.Builder invocationBuilder = buildInvocationBuilder();
        VeterinaryRequest request = buildRequest(fatherDinosaur, motherDinosaur);

        Response response = invocationBuilder.post(Entity.entity(request, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            VeterinaryResponse veterinaryResponse = response.readEntity(VeterinaryResponse.class);
            Dinosaur bredDinosaur = dinosaurFactory.create(
                babyName, fatherDinosaur.getName(), motherDinosaur.getName(),
                DinosaurGender.getGenderFromString(veterinaryResponse.gender),
                DinosaurSpecies.getSpeciesFromString(veterinaryResponse.offspring)
            );
            return Optional.of(bredDinosaur);
        }
        return Optional.empty();
    }

    private Invocation.Builder buildInvocationBuilder() {
        Client client = ClientBuilder.newClient(new ClientConfig().register(VeterinaryResponse.class));
        WebTarget webTarget = client.target(VETERINARY_URL).path("breed");
        return webTarget.request(MediaType.APPLICATION_JSON);
    }

    private VeterinaryRequest buildRequest(Dinosaur fatherDinosaur, Dinosaur motherDinosaur) {
        VeterinaryRequest request = new VeterinaryRequest();
        request.fatherSpecies = fatherDinosaur.getSpecies().toString();
        request.motherSpecies = motherDinosaur.getSpecies().toString();
        return request;
    }
}
