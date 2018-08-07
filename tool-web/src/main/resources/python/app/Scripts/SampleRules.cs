using System;
using Fiddler;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using System.Collections.Generic;

// INTRODUCTION
// This is the FiddlerScript Rules file, which creates some of the menu commands and
// other features of Fiddler. You can edit this file to modify or add new commands.
//
// NOTE: This is the MONO Fiddler version of the script, based on C# rather than
// JScript.NET like the Windows Fiddler version uses. Scripts written for Windows/JScript.NET
// will need to be modified for use on Mono.
//
// The original version of this file is named SampleRules.cs and it is in the
// \Fiddler\ app folder. When Fiddler first starts, it creates a copy named
// CustomRules.cs inside your \Documents\Fiddler2\Scripts folder. If you make a 
// mistake in editing this file, simply delete the CustomRules.cs file and restart
// Fiddler. A fresh copy of the default rules will be created from the original
// sample rules file.

namespace Fiddler
{
    public static class Handlers 
    {
        // The following snippet demonstrates a custom-bound column for the Web Sessions list.
        // See http://fiddler2.com/r/?fiddlercolumns for more info
        [BindUIColumn("Method", 60)]
        public static string FillMethodColumn(Session oS)
        {
         return oS.RequestMethod;
        }

        // The following snippet demonstrates how to create a custom tab that shows simple text
        [BindUITab("Flags")]
        public static string FlagsReport(Session[] arrSess)
        {
            StringBuilder oSB = new StringBuilder();
            for (int i = 0; i < arrSess.Length; i++)
            {
                oSB.AppendLine("SESSION FLAGS");
                oSB.AppendFormat("{0}: {1}\n", arrSess[i].id, arrSess[i].fullUrl);
                foreach(DictionaryEntry sFlag in arrSess[i].oFlags)
                {
                    oSB.AppendFormat("\t{0}:\t\t{1}\n", sFlag.Key, sFlag.Value);
                }
            }
            return oSB.ToString();
        }

        [ToolsAction("ReloadScript")]
        public static void doReload()
        {
            FiddlerObject.ReloadScript();
        }

        // The Main() function runs everytime your FiddlerScript compiles
        public static void Main() 
        {
            FiddlerApplication.UI.SetStatusText(" CustomRules.cs was loaded at: " + DateTime.Now.ToShortTimeString());

            // Uncomment to add a "Server" column containing the response "Server" header, if present
            FiddlerApplication.UI.lvSessions.AddBoundColumn("Server", 50, "@response.server");
        }

        /*
        // You can create a custom menu like so:
        [QuickLinkMenu("&Links")]
        [QuickLinkItem("IE GeoLoc TestDrive", "http://ie.microsoft.com/testdrive/HTML5/Geolocation/Default.html")]
        [QuickLinkItem("FiddlerCore", "http://fiddler2.com/fiddlercore")]
        public static void DoLinksMenu(string sText, string sAction)
        {
            Utilities.LaunchHyperlink(sAction);
        }
        */

        [RulesOption("Hide 304s")]
        [BindPref("fiddlerscript.rules.Hide304s")]
        public static bool m_Hide304s = false;

        // Cause Fiddler to override the Accept-Language header with one of the defined values
        [RulesOption("Request &Japanese Content")]
        public static bool m_Japanese = false;

        // Automatic Authentication
        [RulesOption("&Automatically Authenticate")]
        [BindPref("fiddlerscript.rules.AutoAuth")]
        public static bool m_AutoAuth = false;

        // Cause Fiddler to override the User-Agent header with one of the defined values
        [RulesString("&User-Agents", true)] 
        [BindPref("fiddlerscript.ephemeral.UserAgentString")]
        [RulesStringValue(0,"Netscape &3", "Mozilla/3.0 (Win95; I)")]
        [RulesStringValue(1,"WinPhone7", "Mozilla/4.0 (compatible: MSIE 7.0; Windows Phone OS 7.0; Trident/3.1; IEMobile/7.0; SAMSUNG; SGH-i917)")]
        [RulesStringValue(2,"WinPhone7.5", "Mozilla/5.0 (compatible: MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; SAMSUNG; SGH-i917)")]
        [RulesStringValue(3,"&Safari5 (Win7)", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1")]
        [RulesStringValue(4,"Safari7 (Mac)", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9) AppleWebKit/537.71 (KHTML, like Gecko) Version/7.0 Safari/537.71")]
        [RulesStringValue(5,"iPad", "Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A403 Safari/8536.25")]
        [RulesStringValue(6,"iPhone6", "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A405 Safari/8536.25")]
        [RulesStringValue(7,"IE &6 (XPSP2)", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)")]
        [RulesStringValue(8,"IE &7 (Vista)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1)")]
        [RulesStringValue(9,"IE 8 (Win2k3 x64)", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; WOW64; Trident/4.0)")]
        [RulesStringValue(10,"IE &8 (Win7)", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)")]
        [RulesStringValue(11,"IE 9 (Win7)", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")]
        [RulesStringValue(12,"IE 10 (Win8)", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)")]
        [RulesStringValue(13,"IE 11 (Surface2)", "Mozilla/5.0 (Windows NT 6.3; ARM; Trident/7.0; Touch; rv:11.0) like Gecko")]
        [RulesStringValue(14,"IE 11 (Win8.1)", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko")]
        [RulesStringValue(15,"&Opera", "Opera/9.80 (Windows NT 6.2; WOW64) Presto/2.12.388 Version/12.16")]
        [RulesStringValue(16,"&Firefox 3.6", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.7) Gecko/20100625 Firefox/3.6.7")]
        [RulesStringValue(17,"&Firefox 29", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")]
        [RulesStringValue(18,"&Firefox Phone", "Mozilla/5.0 (Mobile; rv:18.0) Gecko/18.0 Firefox/18.0")]
        [RulesStringValue(19,"&Firefox (Mac)", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0")]
        [RulesStringValue(20,"Chrome", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.69 Safari/537.36")]
        [RulesStringValue(21,"ChromeBook", "Mozilla/5.0 (X11; CrOS armv7l 2913.260.0) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.99 Safari/537.11")]
        [RulesStringValue(22,"GoogleBot Crawler", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")]
        [RulesStringValue(23,"Kindle Fire (Silk)", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-us; Silk/1.0.22.79_10013310) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16 Silk-Accelerated=true")]
        [RulesStringValue(24,"&Custom...", "%CUSTOM%")]
        public static string sUA = null;

        // Cause Fiddler to delay HTTP traffic to simulate typical 56k modem conditions
        [RulesOption("Simulate &Modem Speeds", "Per&formance")]
        public static bool m_SimulateModem = false;

        // Removes HTTP-caching related headers and specifies "no-cache" on requests and responses
        [RulesOption("&Disable Caching", "Per&formance")]
        public static bool m_DisableCaching = false;

        [RulesOption("Cache Always &Fresh", "Per&formance")]
        public static bool m_AlwaysFresh = false;

        [ContextAction("Decode Selected Sessions")]
        public static void DoRemoveEncoding(Session[] arrSessions) 
        {
            for (int x = 0; x < arrSessions.Length; x++)
            {
                arrSessions[x].utilDecodeRequest();
                arrSessions[x].utilDecodeResponse();
            }
            FiddlerApplication.UI.actUpdateInspector(true,true);
        }

        // public static void OnBoot() { }

        // public static void OnShutdown() { }

        /*
        public static void OnAttach() 
        {
            MessageBox.Show("Fiddler is now the system proxy", "Yay");
        }
        */
        /*
        public static void OnDetach() 
        {
            MessageBox.Show("Fiddler is no longer the system proxy");
        }
        */

        public static void OnBeforeRequest(Session oSession) 
        {
            // Sample Rule: Color ASPX requests in RED
            // if (oSession.uriContains(".aspx")) { oSession["ui-color"] = "red";   }

            // Sample Rule: Flag POSTs to fiddler2.com in italics
            // if (oSession.HostnameIs("www.fiddler2.com") && oSession.HTTPMethodIs("POST")) {  oSession["ui-italic"] = "yup";  }

            // Sample Rule: Break requests for URLs containing "/sandbox/"
            // if (oSession.uriContains("/sandbox/")) {
            //     oSession.oFlags["x-breakrequest"] = "yup";   // Existence of the x-breakrequest flag creates a breakpoint; the "yup" value is unimportant.
            // }

            if ((null != gs_ReplaceToken) && (oSession.url.IndexOf(gs_ReplaceToken)>-1)) {   // Case sensitive
                oSession.url = oSession.url.Replace(gs_ReplaceToken, gs_ReplaceTokenWith); 
            }
            if ((null != gs_OverridenHost) && (oSession.host.ToLower() == gs_OverridenHost)) {
                oSession["x-overridehost"] = gs_OverrideHostWith; 
            }

            if ((null!=bpRequestURI) && oSession.uriContains(bpRequestURI)) {
                oSession["x-breakrequest"]="uri";
            }

            if ((null!=bpMethod) && (oSession.HTTPMethodIs(bpMethod))) {
                oSession["x-breakrequest"]="method";
            }

            if ((null!=uiBoldURI) && oSession.uriContains(uiBoldURI)) {
                oSession["ui-bold"]="QuickExec";
            }

            if (m_SimulateModem) {
                // Delay sends by 300ms per KB uploaded.
                oSession["request-trickle-delay"] = "300"; 
                // Delay receives by 150ms per KB downloaded.
                oSession["response-trickle-delay"] = "150"; 
            }

            if (m_DisableCaching) {
                oSession.oRequest.headers.Remove("If-None-Match");
                oSession.oRequest.headers.Remove("If-Modified-Since");
                oSession.oRequest["Pragma"] = "no-cache";
            }

            // User-Agent Overrides
            if (null != sUA) {
                oSession.oRequest["User-Agent"] = sUA; 
            }

            if (m_Japanese) {
                oSession.oRequest["Accept-Language"] = "ja";
            }

            if (m_AutoAuth) {
                // Automatically respond to any authentication challenges using the 
                // current Fiddler user's credentials. You can change (default)
                // to a domain\\username:password string if preferred.
                //
                // WARNING: This setting poses a security risk if remote 
                // connections are permitted!
                oSession["X-AutoAuth"] = "(default)";
            }

            if (m_AlwaysFresh && (oSession.oRequest.headers.Exists("If-Modified-Since") || oSession.oRequest.headers.Exists("If-None-Match")))
            {
                oSession.utilCreateResponseAndBypassServer();
                oSession.responseCode = 304;
                oSession["ui-backcolor"] = "Lavender";
            }
        }


        // This function is called immediately after a set of request headers has
        // been read from the client. This is typically too early to do much useful
        // work, since the body hasn't yet been read, but sometimes it may be useful.
        //
        // For instance, see 
        // http://blogs.msdn.com/b/fiddler/archive/2011/11/05/http-expect-continue-delays-transmitting-post-bodies-by-up-to-350-milliseconds.aspx
        // for one useful thing you can do with this handler.
        //
        // Note: oSession.requestBodyBytes is not available within this function!
    /*
        public static void OnPeekAtRequestHeaders(Session oSession) 
        {
        }
    */

        //
        // If a given session has response streaming enabled, then the OnBeforeResponse function 
        // is actually called AFTER the response was returned to the client.
        //
        // In contrast, this OnPeekAtResponseHeaders function is called before the response headers are 
        // sent to the client (and before the body is read from the server).  Hence this is an opportune time 
        // to disable streaming (oSession.bBufferResponse = true) if there is something in the response headers 
        // which suggests that tampering with the response body is necessary.
        // 
        // Note: oSession.responseBodyBytes is not available within this function!
        //
        public static void OnPeekAtResponseHeaders(Session oSession) 
        {
            //FiddlerApplication.Log.LogFormat("Session {0}: Response header peek shows status is {1}", oSession.id, oSession.responseCode);
            if (m_DisableCaching) {
                oSession.oResponse.headers.Remove("Expires");
                oSession.oResponse["Cache-Control"] = "no-cache";
            }

            if ((bpStatus>0) && (oSession.responseCode == bpStatus)) {
                oSession["x-breakresponse"]="status";
                oSession.bBufferResponse = true;
            }
            
            if ((null!=bpResponseURI) && oSession.uriContains(bpResponseURI)) {
                oSession["x-breakresponse"]="uri";
                oSession.bBufferResponse = true;
            }

            if (m_Hide304s && oSession.responseCode == 304) {
                oSession["ui-hide"] = "true";
            }
        }

        public static void OnBeforeResponse(Session oSession) {
        }

        /*
    // This function executes just before Fiddler returns an error that it has 
    // itself generated (e.g. "DNS Lookup failure") to the client application.
    // These responses will not run through the OnBeforeResponse function above.
    static void OnReturningError(Session oSession) {
    }
*/
/*
    // This function executes after Fiddler finishes processing a Session, regardless
    // of whether it succeeded or failed. Note that this typically runs AFTER the last
    // update of the Web Sessions UI listitem, so you must manually refresh the Session's
    // UI if you intend to change it.
    static void OnDone(Session oSession) { }
*/

        // These static variables are used for simple breakpointing & other QuickExec rules 
        [BindPref("fiddlerscript.ephemeral.bpRequestURI")]
        public static string bpRequestURI = null;

        [BindPref("fiddlerscript.ephemeral.bpResponseURI")]
        public static string bpResponseURI = null;

        [BindPref("fiddlerscript.ephemeral.bpMethod")]
        public static string bpMethod = null;

        static int bpStatus = -1;
        static string uiBoldURI = null;
        static string gs_ReplaceToken = null;
        static string gs_ReplaceTokenWith = null;
        static string gs_OverridenHost = null;
        static string gs_OverrideHostWith = null;

        // The OnExecAction function is called by either the QuickExec box in the Fiddler window,
        // or by the ExecAction.exe command line utility.
        public static bool OnExecAction(string[] sParams)
        {
            FiddlerApplication.UI.SetStatusText("ExecAction: " + sParams[0]);
            string sAction = sParams[0].ToLower();
            switch (sAction) 
            {
            case "bold":
                if (sParams.Length<2) {uiBoldURI=null; FiddlerApplication.UI.SetStatusText("Bolding cleared"); return true;}
                uiBoldURI = sParams[1]; FiddlerApplication.UI.SetStatusText("Bolding requests for " + uiBoldURI);
                return true;
            case "bp":
                MessageBox.Show("bpu = breakpoint request for uri\nbpm = breakpoint request method\nbps=breakpoint response status\nbpafter = breakpoint response for URI");
                return true;
            case "bps":
                if (sParams.Length<2) {bpStatus=-1; FiddlerApplication.UI.SetStatusText("Response Status breakpoint cleared"); return true;}
                bpStatus = Int32.Parse(sParams[1]); FiddlerApplication.UI.SetStatusText("Response status breakpoint for " + sParams[1]);
                return true;
            case "bpv":
            case "bpm":
                if (sParams.Length<2) {bpMethod=null; FiddlerApplication.UI.SetStatusText("Request Method breakpoint cleared"); return true;}
                bpMethod = sParams[1].ToUpper(); FiddlerApplication.UI.SetStatusText("Request Method breakpoint for " + bpMethod);
                return true;
            case "bpu":
                if (sParams.Length<2) {bpRequestURI=null; FiddlerApplication.UI.SetStatusText("RequestURI breakpoint cleared"); return true;}
                bpRequestURI = sParams[1]; 
                FiddlerApplication.UI.SetStatusText("RequestURI breakpoint for "+sParams[1]);
                return true;
            case "bpa":
            case "bpafter":
                if (sParams.Length<2) {bpResponseURI=null; FiddlerApplication.UI.SetStatusText("ResponseURI breakpoint cleared"); return true;}
                bpResponseURI = sParams[1]; 
                FiddlerApplication.UI.SetStatusText("ResponseURI breakpoint for "+sParams[1]);
                return true;
            case "overridehost":
                if (sParams.Length<3) {gs_OverridenHost=null; FiddlerApplication.UI.SetStatusText("Host Override cleared"); return true;}
                gs_OverridenHost = sParams[1].ToLower();
                gs_OverrideHostWith = sParams[2];
                FiddlerApplication.UI.SetStatusText("Connecting to [" + gs_OverrideHostWith + "] for requests to [" + gs_OverridenHost + "]");
                return true;
            case "urlreplace":
                if (sParams.Length<3) {gs_ReplaceToken=null; FiddlerApplication.UI.SetStatusText("URL Replacement cleared"); return true;}
                gs_ReplaceToken = sParams[1];
                gs_ReplaceTokenWith = sParams[2].Replace(" ", "%20");  // Simple helper
                FiddlerApplication.UI.SetStatusText("Replacing [" + gs_ReplaceToken + "] in URIs with [" + gs_ReplaceTokenWith + "]");
                return true;
            case "allbut":
            case "keeponly":
                if (sParams.Length<2) { FiddlerApplication.UI.SetStatusText("Please specify Content-Type to retain during wipe."); return false;}
                FiddlerApplication.UI.actSelectSessionsWithResponseHeaderValue("Content-Type", sParams[1]);
                FiddlerApplication.UI.actRemoveUnselectedSessions();
                FiddlerApplication.UI.lvSessions.SelectedItems.Clear();
                FiddlerApplication.UI.SetStatusText("Removed all but Content-Type: " + sParams[1]);
                return true;
            case "stop":
                FiddlerApplication.UI.actDetachProxy();
                return true;
            case "start":
                FiddlerApplication.UI.actAttachProxy();
                return true;
            case "cls":
            case "clear":
                FiddlerApplication.UI.actRemoveAllSessions();
                return true;
            case "g":
            case "go":
                FiddlerApplication.UI.actResumeAllSessions();
                return true;
            case "goto":
                if (sParams.Length != 2) return false;
                Utilities.LaunchHyperlink("http://www.google.com/search?hl=en&btnI=I%27m+Feeling+Lucky&q=" + Utilities.UrlEncode(sParams[1]));
                return true;
            case "help":
                Utilities.LaunchHyperlink("http://fiddler2.com/r/?quickexec");
                return true;
            case "hide":
                FiddlerApplication.UI.actMinimizeToTray();
                return true;
            case "log":
                FiddlerApplication.Log.LogString((sParams.Length<2) ? "User couldn't think of anything to say..." : sParams[1]);
                return true;
            case "nuke":
                FiddlerApplication.UI.actClearWinINETCache();
                FiddlerApplication.UI.actClearWinINETCookies(); 
                return true;
            case "screenshot":
                FiddlerApplication.UI.actCaptureScreenshot(false);
                return true;
            case "show":
                FiddlerApplication.UI.actRestoreWindow();
                return true;
            case "tail":
                if (sParams.Length<2) { FiddlerApplication.UI.SetStatusText("Please specify # of sessions to trim the session list to."); return false;}
                FiddlerApplication.UI.TrimSessionList(int.Parse(sParams[1]));
                return true;
            case "quit":
                FiddlerApplication.UI.actExit();
                return true;
            case "dump":
                FiddlerApplication.UI.actSelectAll();
                FiddlerApplication.UI.actSaveSessionsToZip(CONFIG.GetPath("Captures") + "dump.saz");
                FiddlerApplication.UI.actRemoveAllSessions();
                FiddlerApplication.UI.SetStatusText("Dumped all sessions to " + CONFIG.GetPath("Captures") + "dump.saz");
                return true;

            default:
                if (sAction.StartsWith("http") || sAction.StartsWith("www")){
                    System.Diagnostics.Process.Start(sParams[0]);
                    return true;
                }
                else
                {
                    FiddlerApplication.UI.SetStatusText("Requested ExecAction: '" + sAction + "' not found. Type HELP to learn more.");
                    return false;
                }
            }
        }

    }
}