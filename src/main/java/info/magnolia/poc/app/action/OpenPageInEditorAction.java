/**
 * This file Copyright (c) 2013 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 *
 *
 * This file is dual-licensed under both the Magnolia
 * Network Agreement and the GNU General Public License.
 * You may elect to use one or the other of these licenses.
 *
 * This file is distributed in the hope that it will be
 * useful, but AS-IS and WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE, or NONINFRINGEMENT.
 * Redistribution, except as permitted by whichever of the GPL
 * or MNA you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or
 * modify this file under the terms of the GNU General
 * Public License, Version 3, as published by the Free Software
 * Foundation.  You should have received a copy of the GNU
 * General Public License, Version 3 along with this program;
 * if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * 2. For the Magnolia Network Agreement (MNA), this file
 * and the accompanying materials are made available under the
 * terms of the MNA which accompanies this distribution, and
 * is available at http://www.magnolia-cms.com/mna.html
 *
 * Any modifications to this file must keep this entire header
 * intact.
 *
 */
package info.magnolia.poc.app.action;

import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.ui.api.action.AbstractAction;
import info.magnolia.ui.api.action.ActionExecutionException;
import info.magnolia.ui.api.location.LocationController;
import info.magnolia.ui.contentapp.detail.DetailLocation;
import info.magnolia.ui.contentapp.detail.DetailView;
import info.magnolia.ui.vaadin.integration.jcr.AbstractJcrNodeAdapter;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Opens a page for editing.
 */
public class OpenPageInEditorAction extends AbstractAction<OpenPageInEditorActionDefinition> {

    private final AbstractJcrNodeAdapter nodeItemToEdit;

    private final LocationController locationController;

    @Inject
    public OpenPageInEditorAction(final OpenPageInEditorActionDefinition definition, AbstractJcrNodeAdapter nodeItemToEdit, LocationController locationController) {
        super(definition);
        this.nodeItemToEdit = nodeItemToEdit;
        this.locationController = locationController;
    }

    @Override
    public void execute() throws ActionExecutionException {
        try {
            Node pageNode = nodeItemToEdit.getJcrItem();

            if (!NodeUtil.isNodeType(pageNode, NodeTypes.Page.NAME)) {
                pageNode = NodeUtil.getNearestAncestorOfType(pageNode, NodeTypes.Page.NAME);
            }
            if (pageNode == null) {
                throw new ActionExecutionException("Not able to resolve page node from " + nodeItemToEdit.getJcrItem().getPath());
            }

            DetailLocation location = new DetailLocation("restNgEditor", "main", DetailView.ViewType.EDIT, pageNode.getPath(), pageNode.getSession().getWorkspace().getName());
            // this causes the app to open the editor tab
            locationController.goTo(location);

        } catch (RepositoryException e) {
            throw new ActionExecutionException(e);
        }
    }
}
