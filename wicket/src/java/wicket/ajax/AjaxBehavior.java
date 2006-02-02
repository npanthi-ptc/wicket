/*
 * $Id$ $Revision:
 * 1.5 $ $Date$
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.ajax;

import wicket.RequestCycle;
import wicket.Response;
import wicket.behavior.AbstractAjaxBehavior;
import wicket.markup.html.PackageResourceReference;

/**
 * The base class of Wicket's defaults ajax implementation.
 * @author Igor Vaynberg
 */
public abstract class AjaxBehavior extends AbstractAjaxBehavior
{
	private static final long serialVersionUID = 1L;

	/** reference to the default ajax support javascript file. */
	private static final PackageResourceReference JAVASCRIPT = new PackageResourceReference(
			AjaxBehavior.class, "wicket-ajax.js");

	/**
	 * 
	 * @see wicket.behavior.AbstractAjaxBehavior#getImplementationId()
	 */
	protected String getImplementationId()
	{
		return "wicket-default";
	}

	/**
	 * 
	 * @return ajax call
	 */
	protected String buildAjaxCall()
	{
		return ("wicketAjaxGet('" + getCallbackUrl() + "');");
	}

	/**
	 * 
	 * @param url
	 * @return ajax call
	 */
	protected String buildAjaxCall(String url)
	{
		return buildAjaxCallRaw("'"+url+"'");
	}

	/**
	 * 
	 * @param javascript
	 * @return ajax call
	 */
	protected String buildAjaxCallRaw(String javascript)
	{
		return ("wicketAjaxGet(" + javascript + ");");
	}

	/**
	 * 
	 * @see wicket.behavior.AbstractAjaxBehavior#onRenderHeadInitContribution(wicket.Response)
	 */
	protected void onRenderHeadInitContribution(final Response response)
	{
		writeJsReference(response, JAVASCRIPT);
	}

	/**
	 * @see wicket.behavior.IBehaviorListener#onRequest()
	 */
	public void onRequest()
	{
		AjaxRequestTarget target = new AjaxRequestTarget();
		RequestCycle.get().setRequestTarget(target);
		respond(target);
	}

	/**
	 * 
	 * @param target
	 */
	protected abstract void respond(AjaxRequestTarget target);
}
