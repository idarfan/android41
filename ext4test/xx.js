Ext.define('MyApp.MyWindow', {	
			extend : 'Ext.Window',
			title : 'welcome',

			initComponent : function() {
				this.items = [{
							xtype : 'textfield',
							name : 'tfName',
							fieldLabel : 'Enter your name'
						}], this.callParent(arguments);
			}
		});
var win = Ext.create('MyApp.MyWindow');
win.show();
