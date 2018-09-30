package banos.ms.beer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Default app config for Tomcat to handle JAX-RS requests.
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application { }
