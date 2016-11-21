/*  MonkeyTalk - a cross-platform functional testing tool
    Copyright (C) 2012 Gorilla Logic, Inc.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package com.gorillalogic.monkeyconsole.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.gorillalogic.monkeyconsole.plugin.FoneMonkeyPlugin;

/**
 * <p>
 * This class represents a preference page that is contributed to the Preferences dialog. By
 * subclassing <code>FieldEditorPreferencePage</code>, we can use the field support built into JFace
 * that allows us to create a page that is small and knows how to save, restore and apply itself.
 * </p>
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that
 * belongs to the main plug-in class. That way, preferences can be accessed directly via the
 * preference store.
 * </p>
 */
public class ProxyServerPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	boolean wasOptIn = true;

	public ProxyServerPreferencePage() {
		super(GRID);
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to
	 * manipulate various types of preferences. Each field editor knows how to save and restore
	 * itself.
	 */
	public void createFieldEditors() {

		// GENERAL PREFERENCES
		addField(new RadioGroupFieldEditor(PreferenceConstants.P_PROXY_SERVER_TYPE, "Proxy Server Type:",2, new String[][] {{"HTTP","HTTP"},{"SOCKS","SOCKS"}}
				,getFieldEditorParent()));
		addField(new IntegerFieldEditor(PreferenceConstants.P_PROXY_SERVER_PORT, "Proxy Server Port:",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(PreferenceConstants.P_RECORD_SERVER_REQUEST, "Record Server Requests",
				getFieldEditorParent()));
		
		addField(new StringFieldEditor(PreferenceConstants.P_EXTERNAL_PROXY_SERVER_HOST, "External Proxy Host:",
				getFieldEditorParent()));
		
		addField(new IntegerFieldEditor(PreferenceConstants.P_EXTERNAL_PROXY_SERVER_PORT, "External Proxy Port:",
				getFieldEditorParent()));
		checkState();

	}

	@Override
	protected void checkState() {
		setErrorMessage(null);
		setValid(true);

		super.checkState();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		checkState();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);

		if (event.getProperty().equals(FieldEditor.VALUE)) {
			checkState();
		}
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(FoneMonkeyPlugin.getDefault().getPreferenceStore());
	}

	@Override
	public boolean performOk() {
		return super.performOk();
	}

}