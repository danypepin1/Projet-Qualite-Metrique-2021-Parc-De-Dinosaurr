package ca.ulaval.glo4002.game.shared.configuration;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

@Service
public class JustInTimeServiceResolver implements JustInTimeInjectionResolver {
    @Inject
    private ServiceLocator serviceLocator;

    @Override
    public boolean justInTimeResolution(Injectee injectee) {
        final Type requiredType = injectee.getRequiredType();

        if (injectee.getRequiredQualifiers().isEmpty() && requiredType instanceof Class) {
            final Class<?> requiredClass = (Class<?>) requiredType;

            if (requiredClass.getName().startsWith("ca.ulaval.glo4002.game.")) {
                final List<ActiveDescriptor<?>> descriptors = ServiceLocatorUtilities.addClasses(
                        serviceLocator, requiredClass);

                return !descriptors.isEmpty();
            }
        }
        return false;
    }
}
