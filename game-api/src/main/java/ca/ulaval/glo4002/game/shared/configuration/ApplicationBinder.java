package ca.ulaval.glo4002.game.shared.configuration;

import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ca.ulaval.glo4002.game.action.entities.ActionRepository;
import ca.ulaval.glo4002.game.action.infrastructure.persistence.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.breed.entities.Veterinary;
import ca.ulaval.glo4002.game.breed.infrastructure.VeterinaryHttpClient;
import ca.ulaval.glo4002.game.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.dinosaur.entities.factories.DinosaurFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.hungerStrategy.HungerStrategyFactory;
import ca.ulaval.glo4002.game.dinosaur.entities.strengthConfiguration.StrengthConfigurationFactory;
import ca.ulaval.glo4002.game.dinosaur.infrastructure.persistence.DinosaurRepositoryInMemory;
import ca.ulaval.glo4002.game.resource.entities.ResourceFactory;
import ca.ulaval.glo4002.game.resource.entities.ResourceRepository;
import ca.ulaval.glo4002.game.resource.infrastructure.persistence.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.sumochallenge.entities.SumoChallengeRepository;
import ca.ulaval.glo4002.game.sumochallenge.infrastructure.SumoChallengeRepositoryInMemory;
import ca.ulaval.glo4002.game.turn.entities.TurnRepository;
import ca.ulaval.glo4002.game.turn.infrastructure.persistence.TurnRepositoryInMemory;

@Provider
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(JustInTimeServiceResolver.class).to(JustInTimeInjectionResolver.class);
        bind(new TurnRepositoryInMemory()).to(TurnRepository.class);
        bind(new ActionRepositoryInMemory()).to(ActionRepository.class);
        bind(new DinosaurRepositoryInMemory(
                new DinosaurFactory(new HungerStrategyFactory(), new StrengthConfigurationFactory()))
        ).to(DinosaurRepository.class);
        bind(new ResourceRepositoryInMemory(new ResourceFactory())).to(ResourceRepository.class);
        bind(VeterinaryHttpClient.class).to(Veterinary.class);
        bind(new SumoChallengeRepositoryInMemory()).to(SumoChallengeRepository.class);
    }
}
